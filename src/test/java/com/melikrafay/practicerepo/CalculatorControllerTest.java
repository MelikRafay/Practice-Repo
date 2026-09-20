package com.melikrafay.practicerepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddNumbers() throws Exception {
        mockMvc.perform(get("/api/calculate").param("a", "3").param("b", "2").param("op", "+"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    void shouldRejectDivideByZero() throws Exception {
        mockMvc.perform(get("/api/calculate").param("a", "10").param("b", "0").param("op", "/"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Division by zero is not allowed"));
    }

    @Test
    void shouldRejectUnsupportedOperation() throws Exception {
        mockMvc.perform(get("/api/calculate").param("a", "1").param("b", "2").param("op", "%"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Unsupported operation"));
    }
}
