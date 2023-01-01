package com.rakbank.repository;

import com.rakbank.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{

    List<Transaction> findByStudentId(Long studentId);

}
