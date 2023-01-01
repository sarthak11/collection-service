package com.rakbank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="transaction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private long studentId;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private String referenceNumber;

    @OneToOne
    @JoinColumn(name = "payment_type_id", nullable = false)
    private PaymentType paymentType;

    @OneToOne
    @JoinColumn(name = "card_details_id", nullable = false)
    private CardDetails cardDetails;

    @Column(nullable = false)
    private Date createdDate;

}
