package com.rakbank.dto;


import com.rakbank.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class TransactionDto implements Serializable {

    private long id;

    private long studentId;

    private Double totalAmount;

    private String referenceNumber;

    private Date createdDate;

    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.studentId = transaction.getStudentId();
        this.totalAmount = transaction.getTotalAmount();
        this.referenceNumber = transaction.getReferenceNumber();
        this.createdDate = transaction.getCreatedDate();
    }
}
