package com.rakbank.service;

import com.rakbank.dto.CardDetailsDto;
import com.rakbank.entity.CardDetails;
import com.rakbank.entity.CardType;
import com.rakbank.exception.CardTypeNotFoundException;
import com.rakbank.repository.CardDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CardDetailsService {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @Autowired
    private CardTypeService cardTypeService;

    public CardDetails saveCardDetails(CardDetailsDto cardDetailsDto) throws CardTypeNotFoundException {
        CardDetails cardDetailsDB = cardDetailsRepository.findByNumber(cardDetailsDto.getNumber());
        if (cardDetailsDB != null) {
            // since card already exists in DB no need to save card details again
            return cardDetailsDB;
        }
        CardType cardType = cardTypeService.getCardTypeByName(cardDetailsDto.getType());
        if (cardType == null) {
            throw new CardTypeNotFoundException("not supported card type: " + cardDetailsDto.getType());
        }
        CardDetails cardDetails = CardDetails.builder()
                .cardType(cardType)
                .number(cardDetailsDto.getNumber())
                .expiryMonth(cardDetailsDto.getExpiryMonth())
                .expiryYear(cardDetailsDto.getExpiryYear())
                .createdDate(new Date())
                .build();
        return cardDetailsRepository.save(cardDetails);
    }
}
