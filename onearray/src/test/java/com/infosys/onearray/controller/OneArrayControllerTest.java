package com.infosys.onearray.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Ordering;
import com.infosys.onearray.dto.OneArrayDto;
import com.infosys.onearray.dto.ThreeArrayDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OneArrayControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    private final OneArrayController oneArrayController;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public OneArrayControllerTest(OneArrayController oneArrayController){
        this.oneArrayController = oneArrayController;
    }

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testOneArray() throws Exception {
        ThreeArrayDto threeArrayDto = new ThreeArrayDto();
        List<Integer> list1 = Arrays.asList(1,2,3,4);
        List<Integer> list2 = Arrays.asList(3,4,15,16);
        List<Integer> list3 = Arrays.asList(6,15,7,2);
        threeArrayDto.setArray1(list1);
        threeArrayDto.setArray2(list2);
        threeArrayDto.setArray3(list3);

        String jsonRequest = objectMapper.writeValueAsString(threeArrayDto);

        MvcResult result = mockMvc.perform(post("/api/makeonearray").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String resultContext = result.getResponse().getContentAsString();
        OneArrayDto oneArrayDto = objectMapper.readValue(resultContext, OneArrayDto.class);

        assertNotNull(oneArrayDto);
        assertNotNull(oneArrayDto.getArray());
        assertEquals(Ordering.natural().isOrdered(oneArrayDto.getArray()), Boolean.TRUE);
        Set<Integer> set = new HashSet<>(oneArrayDto.getArray());
        assertEquals(oneArrayDto.getArray().size(), set.size());
    }
}
