package com.rakbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class FeeReceiptsDto {

    private StudentResponse student;

    private List<FeeReceiptDto> feeReceipts;

}
