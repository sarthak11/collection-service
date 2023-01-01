package com.rakbank.service;

import com.rakbank.entity.PaymentType;
import com.rakbank.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    public PaymentType getPaymentTypeByName(String name) {
        return paymentTypeRepository.findByName(name);
    }

}
