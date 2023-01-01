package com.rakbank.dto;


import com.rakbank.entity.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentTypeDto implements Serializable {

    private long id;

    private String name;

    public PaymentTypeDto(PaymentType paymentType) {
        this.id = paymentType.getId();
        this.name = paymentType.getName();
    }

}
