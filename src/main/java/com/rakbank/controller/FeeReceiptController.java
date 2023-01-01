package com.rakbank.controller;

import com.rakbank.dto.FeeReceiptsDto;
import com.rakbank.exception.FeeReceiptException;
import com.rakbank.service.FeeReceiptService;
import com.rakbank.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/feeReceipt")
public class FeeReceiptController {

    @Autowired
    private FeeReceiptService feeReceiptService;

    @RequestMapping(value = "/fetch/{studentId}", method = RequestMethod.GET, produces = Constants.APPLICATION_JSON)
    public ResponseEntity save(@PathVariable Long studentId) {
        try {
            FeeReceiptsDto response = feeReceiptService.getReceipts(studentId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (FeeReceiptException e) {
            return new ResponseEntity<>(Arrays.asList(), HttpStatus.OK);
        }
    }

}
