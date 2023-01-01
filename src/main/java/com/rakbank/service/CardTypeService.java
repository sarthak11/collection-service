package com.rakbank.service;

import com.rakbank.entity.CardType;
import com.rakbank.repository.CardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTypeService {

    @Autowired
    private CardTypeRepository cardTypeRepository;

    public CardType getCardTypeByName(String name) {
        return cardTypeRepository.findByName(name);
    }

}
