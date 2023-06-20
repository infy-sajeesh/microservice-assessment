package com.infosys.triangle.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infosys.triangle.utility.Constants;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Assertions;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TriangleControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    private final TriangleController triangleController;

    @Autowired
    public TriangleControllerTest(TriangleController triangleController){
        this.triangleController = triangleController;
    }

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testEQTriangleType() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/TriangleType?a=12&b=12&c=12")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(Constants.EQ, result.trim());
    }

    @Test
    public void testISTriangleType() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/TriangleType?a=12&b=12&c=10")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(Constants.IS, result.trim());
    }

    @Test
    public void testSCTriangleType() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/TriangleType?a=11&b=12&c=10")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(Constants.SC, result.trim());
    }

    @Test
    public void testNotNumberTriangleType() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/TriangleType?a=11&b=12&c=AA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Gson g = new Gson();
        Map<String, String> response = g.fromJson(result, new TypeToken<HashMap<String, Object>>(){}.getType());
        String message = response.get("message");
        Assertions.assertEquals("Numbers only allowed", message.trim());
    }
}
