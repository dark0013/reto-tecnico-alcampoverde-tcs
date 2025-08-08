package com.acampoverde.ms_client_banking.infrastructure.in.rest.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.acampoverde.ms_client_banking.infrastructure.in.rest.dto.CustomerDto;
import com.acampoverde.ms_client_banking.infrastructure.in.rest.handler.CustomerHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerHandler customerHandler;

    @InjectMocks
    private CustomerController customerController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnAllCustomers() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setId(1);
        dto.setName("Alice");
        dto.setGender("F");
        dto.setAge(30);
        dto.setIdentification("1234567890");
        dto.setAddress("123 Main St");
        dto.setTelephone("+1234567890");

        when(customerHandler.findAllCustomers()).thenReturn(List.of(dto));

        mockMvc.perform(get("/v1/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void shouldReturnCustomerById() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setId(1);
        dto.setName("Bob");
        dto.setGender("M");
        dto.setAge(25);
        dto.setIdentification("9876543210");
        dto.setAddress("456 Elm St");
        dto.setTelephone("+0987654321");

        when(customerHandler.findCustomerById(1)).thenReturn(dto);

        mockMvc.perform(get("/v1/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldSaveCustomer() throws Exception {
        CustomerDto input = new CustomerDto();
        input.setName("Carol");
        input.setGender("F");
        input.setAge(28);
        input.setIdentification("111222333");
        input.setAddress("789 Oak St");
        input.setTelephone("+111222333");

        CustomerDto saved = new CustomerDto();
        saved.setId(10);
        saved.setName(input.getName());
        saved.setGender(input.getGender());
        saved.setAge(input.getAge());
        saved.setIdentification(input.getIdentification());
        saved.setAddress(input.getAddress());
        saved.setTelephone(input.getTelephone());

        when(customerHandler.saveCustomer(any(CustomerDto.class))).thenReturn(saved);

        mockMvc.perform(post("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/v1/customer/10"));
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setId(5);
        dto.setName("Updated Name");
        dto.setGender("M");
        dto.setAge(35);
        dto.setIdentification("555666777");
        dto.setAddress("Updated Address");
        dto.setTelephone("+555666777");

        when(customerHandler.updateCustomer(any(CustomerDto.class))).thenReturn(dto);

        mockMvc.perform(put("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.id").value(5));
    }

    @Test
    void shouldDeleteCustomer() throws Exception {
        doNothing().when(customerHandler).deleteCustomer(1);

        mockMvc.perform(delete("/v1/customer/1"))
                .andExpect(status().isNoContent());

        verify(customerHandler, times(1)).deleteCustomer(1);
    }

    // Opcional: test validación fallo (ejemplo con nombre vacío)
    @Test
    void shouldFailValidationOnSaveCustomer() throws Exception {
        CustomerDto input = new CustomerDto();
        input.setName(""); // nombre inválido (NotBlank)
        input.setGender("F");
        input.setAge(28);
        input.setIdentification("111222333");
        input.setAddress("789 Oak St");
        input.setTelephone("+111222333");

        mockMvc.perform(post("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }
}
