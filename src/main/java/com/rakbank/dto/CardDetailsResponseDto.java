package com.rakbank.dto;

import com.rakbank.entity.CardDetails;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@Setter
public class CardDetailsResponseDto implements Serializable {

    private String type;

    private String number;

    private String expiryMonth;

    private String expiryYear;

    public CardDetailsResponseDto(CardDetails cardDetails) {
        this.type = cardDetails.getCardType().getName();
        this.number = new String(Base64.getDecoder().decode(cardDetails.getNumber()), StandardCharsets.UTF_8);;
        this.expiryMonth = cardDetails.getExpiryMonth();
        this.expiryYear = cardDetails.getExpiryYear();
    }

}
