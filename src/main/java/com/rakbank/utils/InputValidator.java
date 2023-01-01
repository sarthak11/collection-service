package com.rakbank.utils;

import com.rakbank.dto.CardDetailsDto;
import com.rakbank.dto.FeeCollectionDto;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class InputValidator {

    public boolean isValidFeeCollectionRequest(Long studentId, FeeCollectionDto feeCollectionDto) {
        if (studentId <= 0) {
            return false;
        }

        return (isValidCard(feeCollectionDto.getCardDetails()) && isNumberNonZeroAndPositive(feeCollectionDto.getTotal()));
    }

    public boolean isValidCard(CardDetailsDto cardDetailsDto) {
        int month = Integer.valueOf(cardDetailsDto.getExpiryMonth());
        int year = Integer.valueOf(cardDetailsDto.getExpiryYear());

        return (isBase64(cardDetailsDto.getNumber()) && isValidMonth(month) && isValidYear(year));
    }

    public boolean isNumberNonZeroAndPositive(double number) {
        return number > 0;
    }

    public boolean isBase64(String str) {
        try {
            Base64.getDecoder().decode(str);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isValidMonth(int month) {
        return (month >= 1 && month <= 12);
    }

    public boolean isValidYear(int year) {
        return (year >= 2022 && year <= 2050);
    }

}
