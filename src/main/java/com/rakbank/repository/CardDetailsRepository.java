package com.rakbank.repository;

import com.rakbank.entity.CardDetails;
import org.springframework.data.repository.CrudRepository;

public interface CardDetailsRepository extends CrudRepository<CardDetails, Long> {

    CardDetails findByNumber(String number);

}
