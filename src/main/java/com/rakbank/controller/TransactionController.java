package com.rakbank.controller;

import com.rakbank.dto.ErrorResponse;
import com.rakbank.dto.FeeCollectionDto;
import com.rakbank.dto.SuccessResponse;
import com.rakbank.exception.FeeCollectionException;
import com.rakbank.service.TransactionService;
import com.rakbank.utils.Constants;
import com.rakbank.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private InputValidator inputValidator;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/collectFee/{studentId}", method = RequestMethod.POST, produces = Constants.APPLICATION_JSON)
    public ResponseEntity save(@PathVariable Long studentId, @RequestBody FeeCollectionDto feeCollectionDto) {
        if (inputValidator.isValidFeeCollectionRequest(studentId, feeCollectionDto)) {
            try {
                transactionService.collectFee(studentId, feeCollectionDto);
            } catch (FeeCollectionException e) {
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "invalid input"), HttpStatus.BAD_REQUEST);
    }

}
