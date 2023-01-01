package com.rakbank.repository;

import com.rakbank.entity.CardType;
import org.springframework.data.repository.CrudRepository;

public interface CardTypeRepository extends CrudRepository<CardType, Long> {

    CardType findByName(String name);

}
