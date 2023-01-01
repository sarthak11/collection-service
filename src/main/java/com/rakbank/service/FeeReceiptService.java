package com.rakbank.service;

import com.rakbank.dto.*;
import com.rakbank.entity.Transaction;
import com.rakbank.entity.TutionFee;
import com.rakbank.entity.TutionTransactionMapping;
import com.rakbank.exception.FeeReceiptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeeReceiptService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TutionTransactionMappingService tutionTransactionMappingService;

    @Autowired
    private StudentManagementService studentManagementService;

    public FeeReceiptsDto getReceipts(Long studentId) throws FeeReceiptException {
        List<FeeReceiptDto> feeReceipts  = new ArrayList<>();
        StudentResponse studentResponse = studentManagementService.getStudentDetailsById(studentId);
        if (studentResponse == null) {
            throw new FeeReceiptException("student with id: " + studentId + " not found!");
        }
        List<Transaction> transactions = transactionService.getTransactionsByStudentId(studentId);
        for(Transaction transaction: transactions) {
            List<TutionTransactionMapping> mappings = tutionTransactionMappingService.getMappingsByTransaction(transaction);
            List<TutionDetailsResponseDto> tutionDetails = new ArrayList<>();
            for (TutionTransactionMapping mapping: mappings) {
                tutionDetails.add(new TutionDetailsResponseDto(new TutionFeeDto(mapping.getTutionFee()), mapping.getQuantity()));
            }
            feeReceipts.add(new FeeReceiptDto(new PaymentTypeDto(transaction.getPaymentType()),
                    new CardDetailsResponseDto(transaction.getCardDetails()), new TransactionDto(transaction),
                    tutionDetails));
        }
        return new FeeReceiptsDto(studentResponse, feeReceipts);
    }
}
