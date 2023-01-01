package com.rakbank.repository;

import com.rakbank.entity.PaymentType;
import org.springframework.data.repository.CrudRepository;

public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {

    PaymentType findByName(String name);

}
