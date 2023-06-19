package com.infosys.triangle.controller;

import com.infosys.triangle.exception.InvalidDataException;
import com.infosys.triangle.utility.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TriangleControllerTest {

    private final TriangleController triangleController;

    @Autowired
    public TriangleControllerTest(TriangleController triangleController){
        this.triangleController = triangleController;
    }

    @Test
    public void testEQTriangleType(){
        ResponseEntity<String> response;
        String triangleType;
        response = triangleController.getTriangleType("1", "1", "1");
        triangleType = response.getBody();
        assertNotNull(triangleType);
        assertEquals(triangleType, Constants.EQ);
    }

    @Test
    public void testISTriangleType(){
        ResponseEntity<String> response;
        String triangleType;
        response = triangleController.getTriangleType("1", "1", "2");
        triangleType = response.getBody();
        assertNotNull(triangleType);
        assertEquals(triangleType, Constants.IS);
    }

    @Test
    public void testSCTriangleType(){
        ResponseEntity<String> response;
        String triangleType;
        response = triangleController.getTriangleType("1", "3", "2");
        triangleType = response.getBody();
        assertNotNull(triangleType);
        assertEquals(triangleType, Constants.SC);
    }

    @Test
    public void testNotNumberTriangleType(){
        InvalidDataException invalidDataException = assertThrows(InvalidDataException.class,
                ()->triangleController.getTriangleType("A", "2", "3"));
    }
}
