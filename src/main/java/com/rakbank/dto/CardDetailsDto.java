package com.rakbank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetailsDto {

    private String type;

    private String number;

    private String expiryMonth;

    private String expiryYear;

}
