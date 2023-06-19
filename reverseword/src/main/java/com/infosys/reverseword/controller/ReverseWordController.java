package com.infosys.reverseword.controller;

import com.infosys.reverseword.service.ReverseWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ReverseWords")
public class ReverseWordController {

    private final ReverseWordService reverseWordService;

    @Autowired
    public ReverseWordController(ReverseWordService reverseWordService){
        this.reverseWordService = reverseWordService;
    }

    @GetMapping
    public ResponseEntity<String> getReverseWord(String sentence){
        return new ResponseEntity<>(reverseWordService.reverseWords(sentence), HttpStatus.OK);
    }
}
