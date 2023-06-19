package com.infosys.fibonacci.controller;

import com.infosys.fibonacci.exception.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FibonacciControllerTest {

    private FibonacciController fibonacciController;

    @Autowired
    public FibonacciControllerTest(FibonacciController fibonacciController){
        this.fibonacciController = fibonacciController;
    }

    @Test
    public void testFibonacci(){
        ResponseEntity<Long> response = fibonacciController.getFibonacci("10");
        Long num = response.getBody();
        assertEquals(num,55);
    }

    @Test
    public void testErrorFibonacci(){
        InvalidDataException invalidDataException = assertThrows(InvalidDataException.class,
                ()->fibonacciController.getFibonacci("AA"));
    }
}
