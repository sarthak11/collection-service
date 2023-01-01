package com.rakbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class FeeReceiptDto {

    private PaymentTypeDto paymentType;

    private CardDetailsResponseDto cardDetails;

    private TransactionDto transaction;

    private List<TutionDetailsResponseDto> tutionDetails;

}
