package com.infosys.fibonacci.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class FibonacciControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private FibonacciController fibonacciController;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Autowired
    public FibonacciControllerTest(FibonacciController fibonacciController){
        this.fibonacciController = fibonacciController;
    }

    @Test
    public void testFibonacciMockMvc() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/Fibonacci?num=10")
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(55, Long.parseLong(result));
    }

    @Test
    public void testFibonacciErrorMockMvc() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/Fibonacci?num=AA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Gson g = new Gson();
        Map<String, String> response = g.fromJson(result, new TypeToken<HashMap<String, Object>>(){}.getType());
        String message = response.get("message");
        Assertions.assertEquals("Numbers only allowed", message.trim());
        Assertions.assertTrue("Numbers only allowed".equals(message.trim()));

    }
}
