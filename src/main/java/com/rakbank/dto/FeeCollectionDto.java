package com.rakbank.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeeCollectionDto {

    private Double total;

    private String paymentType;

    private List<TutionDetailsDto> tutionDetails;

    private CardDetailsDto cardDetails;

}
