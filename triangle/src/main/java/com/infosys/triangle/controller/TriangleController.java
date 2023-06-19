package com.infosys.triangle.controller;

import com.infosys.triangle.service.TriangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/TriangleType")
public class TriangleController {

    private TriangleService triangleService;

    @Autowired
    public TriangleController(TriangleService triangleService){
        this.triangleService = triangleService;
    }

    @GetMapping
    public ResponseEntity<String> getTriangleType(String a, String b, String c){
        return new ResponseEntity<>(triangleService.checkTriangleType(a,b,c), HttpStatus.OK);
    }
}
