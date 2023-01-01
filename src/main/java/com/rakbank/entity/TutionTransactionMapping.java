package com.rakbank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="tution_transaction_mapping")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutionTransactionMapping {

    @Id
    @GeneratedValue()
    private long id;

    @ManyToOne
    @JoinColumn(name = "tution_fee_id", nullable = false)
    private TutionFee tutionFee;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Date createdDate;

}
