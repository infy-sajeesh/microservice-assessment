package com.infosys.fibonacci.controller;

import com.infosys.fibonacci.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Fibonacci")
public class FibonacciController {

    private final FibonacciService fibonacciService;

    @Autowired
    public FibonacciController(FibonacciService fibonacciService){
        this.fibonacciService = fibonacciService;
    }

    @GetMapping
    public ResponseEntity<Long> getFibonacci(String num){
        //return new ResponseEntity(fibonacciService.findFibonacci(num), HttpStatus.OK);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noCache())
                .body(fibonacciService.findFibonacci(num));
    }
}
