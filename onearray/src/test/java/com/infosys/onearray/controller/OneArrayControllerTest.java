package com.infosys.onearray.controller;

import com.google.common.collect.Ordering;
import com.infosys.onearray.dto.OneArrayDto;
import com.infosys.onearray.dto.ThreeArrayDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OneArrayControllerTest {

    private final OneArrayController oneArrayController;

    @Autowired
    public OneArrayControllerTest(OneArrayController oneArrayController){
        this.oneArrayController = oneArrayController;
    }

    @Test
    public void testOneArray(){
        ThreeArrayDto threeArrayDto = new ThreeArrayDto();
        List<Integer> list1 = Arrays.asList(1,2,3,4);
        List<Integer> list2 = Arrays.asList(3,4,15,16);
        List<Integer> list3 = Arrays.asList(6,15,7,2);
        threeArrayDto.setArray1(list1);
        threeArrayDto.setArray2(list2);
        threeArrayDto.setArray3(list3);

        ResponseEntity<OneArrayDto> response = oneArrayController.getOneArray(threeArrayDto);
        OneArrayDto oneArrayDto = response.getBody();

        assertNotNull(oneArrayDto);
        assertNotNull(oneArrayDto.getArray());
        assertEquals(Ordering.natural().isOrdered(oneArrayDto.getArray()), Boolean.TRUE);
        Set<Integer> set = new HashSet<>(oneArrayDto.getArray());
        assertEquals(oneArrayDto.getArray().size(), set.size());
    }
}
