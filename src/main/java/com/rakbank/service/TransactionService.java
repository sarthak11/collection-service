package com.rakbank.service;

import com.rakbank.dto.FeeCollectionDto;
import com.rakbank.dto.StudentResponse;
import com.rakbank.dto.TutionDetailsDto;
import com.rakbank.entity.*;
import com.rakbank.exception.CardTypeNotFoundException;
import com.rakbank.exception.FeeCollectionException;
import com.rakbank.exception.PaymentTypeNotFoundException;
import com.rakbank.exception.TutionFeeNotFoundException;
import com.rakbank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CardDetailsService cardDetailsService;

    @Autowired
    private PaymentTypeService paymentTypeService;

    @Autowired
    private TutionFeeService tutionFeeService;

    @Autowired
    private StudentManagementService studentManagementService;

    @Autowired
    private TutionTransactionMappingService tutionTransactionMappingService;

    public boolean collectFee(Long studentId, FeeCollectionDto feeCollectionDto) throws FeeCollectionException {
        PaymentType paymentType = null;
        StudentResponse studentResponse = studentManagementService.getStudentDetailsById(studentId);
        if (studentResponse == null) {
            throw new FeeCollectionException("student with id: " + studentId + " not found!");
        }
        try {
            validateTutionDetails(feeCollectionDto.getTutionDetails());
            paymentType = getPaymentTypeByName(feeCollectionDto.getPaymentType());
        } catch (TutionFeeNotFoundException | PaymentTypeNotFoundException e) {
            throw new FeeCollectionException(e.getMessage());
        }
        saveTransactionDetails(studentId, paymentType, feeCollectionDto, feeCollectionDto.getTutionDetails());
        return true;
    }

    public List<Transaction> getTransactionsByStudentId(Long studentId) {
       return transactionRepository.findByStudentId(studentId);
    }

    private PaymentType getPaymentTypeByName(String name) throws PaymentTypeNotFoundException {
        PaymentType paymentType = paymentTypeService.getPaymentTypeByName(name);
        if (paymentType == null) {
            throw new PaymentTypeNotFoundException("not supported payment type: " + name);
        }
        return paymentType;
    }

    private void validateTutionDetails(List<TutionDetailsDto> tutionDetails) throws TutionFeeNotFoundException {
        for (TutionDetailsDto tutionDetailsDto : tutionDetails) {
            TutionFee tutionFee = tutionFeeService.findTutionFeeById(tutionDetailsDto.getTutionFeeId());
            if (tutionFee == null) {
                throw new TutionFeeNotFoundException("tution id: " + tutionDetailsDto.getTutionFeeId()+ " not found");
            }
        }
    }

    private void saveTransactionDetails(Long studentId, PaymentType paymentType, FeeCollectionDto feeCollectionDto,
                                        List<TutionDetailsDto> tutionDetailsDtoList) {
        transactionTemplate.execute(status -> {
            CardDetails cardDetails = null;
            try {
                cardDetails = cardDetailsService.saveCardDetails(feeCollectionDto.getCardDetails());
            } catch (CardTypeNotFoundException e) {
                return false;
            }
            Transaction transaction = Transaction.builder()
                    .studentId(studentId)
                    .totalAmount(feeCollectionDto.getTotal())
                    .referenceNumber(UUID.randomUUID().toString())
                    .paymentType(paymentType)
                    .cardDetails(cardDetails)
                    .createdDate(new Date())
                    .build();
            transaction = transactionRepository.save(transaction);
            for (TutionDetailsDto tutionDetailsDto : tutionDetailsDtoList) {
                TutionFee tutionFee = tutionFeeService.findTutionFeeById(tutionDetailsDto.getTutionFeeId());
                if (tutionFee == null) {
                    return false;
                }
                tutionTransactionMappingService.saveMapping(tutionFee, transaction, tutionDetailsDto.getQuantity());
            }
            return true;
        });
    }
}
