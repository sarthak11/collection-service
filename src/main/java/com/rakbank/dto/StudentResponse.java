package com.rakbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class StudentResponse implements Serializable {

    private long id;

    private long uniqueId;

    private String name;

    private int grade;

    private String mobileNumber;

    private String schoolName;
}
