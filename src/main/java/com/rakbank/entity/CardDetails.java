package com.rakbank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="card_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails {

    @Id
    @GeneratedValue()
    private long id;

    @OneToOne
    @JoinColumn(name = "card_type_id", nullable = false)
    private CardType cardType;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String expiryMonth;

    @Column(nullable = false)
    private String expiryYear;

    @Column(nullable = false)
    private Date createdDate;
    
}
