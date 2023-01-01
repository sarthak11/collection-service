package com.rakbank.repository;

import com.rakbank.entity.Transaction;
import com.rakbank.entity.TutionTransactionMapping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TutionTransactionMappingRepository extends CrudRepository<TutionTransactionMapping, Long> {

    List<TutionTransactionMapping> findByTransaction(Transaction transaction);

}
