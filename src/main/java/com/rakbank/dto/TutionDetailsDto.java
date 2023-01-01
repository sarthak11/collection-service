package com.rakbank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TutionDetailsDto {

    private Long tutionFeeId;

    private int quantity;

}
