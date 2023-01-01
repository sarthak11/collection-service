package com.rakbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TutionDetailsResponseDto {

    private TutionFeeDto tutionFee;

    private int quantity;

}
