package com.rakbank.dto;

import com.rakbank.entity.TutionFee;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TutionFeeDto implements Serializable {

    private long id;

    private int grade;

    private double fee;

    public TutionFeeDto(TutionFee tutionFee) {
        this.id = tutionFee.getId();
        this.grade = tutionFee.getGrade();
        this.fee = tutionFee.getFee();
    }

}
