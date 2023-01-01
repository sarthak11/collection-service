package com.rakbank.service;

import com.rakbank.entity.Transaction;
import com.rakbank.entity.TutionFee;
import com.rakbank.entity.TutionTransactionMapping;
import com.rakbank.repository.TutionTransactionMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TutionTransactionMappingService {

    @Autowired
    private TutionTransactionMappingRepository tutionTransactionMappingRepository;

    public void saveMapping(TutionFee tutionFee, Transaction transaction, int quantity) {
        TutionTransactionMapping mapping = TutionTransactionMapping.builder()
                .tutionFee(tutionFee)
                .transaction(transaction)
                .quantity(quantity)
                .createdDate(new Date())
                .build();
        tutionTransactionMappingRepository.save(mapping);
    }

    public List<TutionTransactionMapping> getMappingsByTransaction(Transaction transaction) {
        return tutionTransactionMappingRepository.findByTransaction(transaction);
    }
}
