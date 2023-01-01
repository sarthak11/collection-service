package com.rakbank.service;

import com.rakbank.entity.TutionFee;
import com.rakbank.repository.TutionFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutionFeeService {

    @Autowired
    private TutionFeeRepository tutionFeeRepository;

    public TutionFee findTutionFeeById(Long id) {
        return tutionFeeRepository.findById(id).orElse(null);
    }
}
