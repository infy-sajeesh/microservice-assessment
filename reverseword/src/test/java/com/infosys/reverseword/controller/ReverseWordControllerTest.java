package com.infosys.reverseword.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infosys.reverseword.exception.InvalidDataException;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ReverseWordControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    private final ReverseWordController reverseWordController;

    @Autowired
    public ReverseWordControllerTest(ReverseWordController reverseWordController){
        this.reverseWordController = reverseWordController;
    }

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testReverseWord() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/ReverseWords?sentence=Every thing is okay")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("yrevE gniht si yako", result.trim());
    }

    @Test
    public void testEmptyWord() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/ReverseWords?sentence=")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Gson g = new Gson();
        Map<String, String> response = g.fromJson(result, new TypeToken<HashMap<String, Object>>(){}.getType());
        String message = response.get("message");
        Assertions.assertEquals("Words cannot be null or blank", message.trim());
    }

    @Test
    public void testBlankWord() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/ReverseWords?sentence=  ")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Gson g = new Gson();
        Map<String, String> response = g.fromJson(result, new TypeToken<HashMap<String, Object>>(){}.getType());
        String message = response.get("message");
        Assertions.assertEquals("Words cannot be null or blank", message.trim());
    }
}
