package com.infosys.fibonacci.service;

import com.infosys.fibonacci.enumeration.ErrorCode;
import com.infosys.fibonacci.exception.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FibonacciService {

    public Long findFibonacci(String num){
        long l;
        try {
            l = Long.parseLong(num);
        } catch (NumberFormatException nfe) {
            throw new InvalidDataException(ErrorCode.INVALID_DATA, "Numbers only allowed");
        }

        Long firstNum = 0L;
        Long secondNum = 1L;
        Long nextNum = 0L;

        for (long i = 1; i < l; ++i) {
            nextNum = firstNum + secondNum;
            firstNum = secondNum;
            secondNum = nextNum;
        }
        return nextNum;
    }
}
