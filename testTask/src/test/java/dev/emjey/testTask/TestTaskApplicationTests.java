package dev.emjey.testTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.emjey.testTask.entity.Customer;
import dev.emjey.testTask.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TestTaskApplicationTests {
 
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final Customer[] customers = new Customer[]{
            new Customer(1L, "EmJey", "Dev", "emjey@gmail.com", "des1"),
            new Customer(2L, "Ali", "Hosseini", "ali@gmail.com", "des2"),
            new Customer(3L, "Emad", "Ranjbar", "emad@gmail.com", "des3"),

    };

    @BeforeEach
    void setup() {
        customerRepository.saveAll(Arrays.asList(customers));
    }

    @AfterEach
    void clear() {
        customerRepository.deleteAll(customerRepository.findAll());
    }

    @Test
    public void testGetCustomerById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customer/1");

        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(customers[0].getFirstName()))
                .andExpect(jsonPath("$.lastName").value(customers[0].getLastName()))
                .andExpect(jsonPath("$.email").value(customers[0].getEmail()))
                .andExpect(jsonPath("$.description").value(customers[0].getDescription()));

    }

    @Test
    public void testGetAllCustomers() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customer/all");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(customers.length))
                .andExpect(jsonPath("$.[?(@.id == 1 && @.firstName ==\"EmJey\" && @.lastName == \"Dev\" && @.email ==\"emjey@gmail.com\")]").exists())
                .andExpect(jsonPath("$.[?(@.id == 2 && @.firstName ==\"Ali\" && @.lastName == \"Hosseini\" && @.email ==\"ali@gmail.com\")]").exists());

    }

    @Test
    public void validCustomerCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Customer(4L, "EmJey", "Rahgozar", "rahgozar@gmail.com", "8585")));

        mockMvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    public void invalidCustomerCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Customer(5L, " ", null, null, "    ")));

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());

    }

}
