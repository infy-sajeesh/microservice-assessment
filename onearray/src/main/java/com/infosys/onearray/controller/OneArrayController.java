package com.infosys.onearray.controller;

import com.infosys.onearray.dto.OneArrayDto;
import com.infosys.onearray.dto.ThreeArrayDto;
import com.infosys.onearray.enumeration.ErrorCode;
import com.infosys.onearray.exception.ExceptionResponseCreator;
import com.infosys.onearray.service.OneArrayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/makeonearray")
public class OneArrayController {

    private final OneArrayService oneArrayService;
    private final ExceptionResponseCreator exceptionResponseCreator;

    @Autowired
    public OneArrayController(OneArrayService oneArrayService, ExceptionResponseCreator exceptionResponseCreator){
        this.oneArrayService = oneArrayService;
        this.exceptionResponseCreator = exceptionResponseCreator;
    }

    @PostMapping
    public ResponseEntity<OneArrayDto> getOneArray(@Valid @RequestBody ThreeArrayDto threeArrayDto){
        return new ResponseEntity<>(oneArrayService.getOneArray(threeArrayDto), HttpStatus.CREATED);
    }
}
