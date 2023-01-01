package com.rakbank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="tution_fee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutionFee {

    @Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private double fee;

    @Column(nullable = false)
    private Date createdDate;

}
