package com.infosys.reverseword.controller;

import com.infosys.reverseword.exception.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ReverseWordControllerTest {

    private final ReverseWordController reverseWordController;

    @Autowired
    public ReverseWordControllerTest(ReverseWordController reverseWordController){
        this.reverseWordController = reverseWordController;
    }

    @Test
    public void testReverseWord(){
        String reverse = "yrevE gniht si yako";
        ResponseEntity<String> response = reverseWordController.getReverseWord("Every thing is okay");
        String word = response.getBody();
        assertEquals(word.trim(),reverse);
    }

    @Test
    public void testEmptyWord(){
        InvalidDataException invalidDataException = assertThrows(InvalidDataException.class,
                ()->reverseWordController.getReverseWord(""));
    }

    @Test
    public void testBlankWord(){
        InvalidDataException invalidDataException = assertThrows(InvalidDataException.class,
                ()->reverseWordController.getReverseWord("   "));
    }
}
