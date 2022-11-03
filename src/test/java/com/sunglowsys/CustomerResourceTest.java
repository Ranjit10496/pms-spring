package com.sunglowsys;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.resource.CustomerResource;
import com.sunglowsys.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CustomerResource.class)
public class CustomerResourceTest {
    private static String EXPEXTED_RESULT = "{\"id\": 1,"+
            "\"firstName\" : \"Ranjit\","+
            "\"lastName\" : \"Kumar\","+
            "\"email\" : \"ranjit10496@gmail.com\","+
            "\"mobile\" : \"8433175578\","+
            "\"gender\" : \"Male\","+
            "\"zipcode\" : \"204211\"}";



    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;

    private static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Ranjit");
        customer.setLastName("Kumar");
        customer.setEmail("ranjit10496@gmail.com");
        customer.setMobile("8433175578");
        customer.setGender("Male");
        customer.setZipcode("204211");
        return customer;
    }
    private static Customer createCustomer2() {
        Customer customer = new Customer();
        customer.setFirstName("Nilam");
        customer.setLastName("Singh");
        customer.setEmail("nilam@gmail.com");
        customer.setMobile("9897806165");
        customer.setGender("Female");
        customer.setZipcode("204144");
        return customer;
    }
    @Test
    void createCustomerTest() throws Exception {
        Customer mockCustomer = createCustomer();
        mockCustomer.setId(1l);
        when(customerService.save(any())).thenReturn(mockCustomer);
        MvcResult result = mockMvc.perform(post("/api/customers")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockCustomer)))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT,result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);

    }
    @Test
    void updateCustomerTest() throws Exception {
        Customer mockCustomer = createCustomer();
        mockCustomer.setId(1l);
        when(customerService.update(any())).thenReturn(mockCustomer);
        MvcResult result = mockMvc.perform(put("/api/customers")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockCustomer)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getCustomerTest() throws Exception {
        Customer mockCustomer = createCustomer();
        mockCustomer.setId(1l);

        when(customerService.findOne(anyLong())).thenReturn(Optional.of(mockCustomer));

        MvcResult result = mockMvc.perform(get("/api/customers/1")
                .accept(TestUtil.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getAllCustomerTest() throws Exception {
        Customer mockCustomer1 = createCustomer();
        mockCustomer1.setId(1l);
        Customer mockCustomer2 = createCustomer2();
        mockCustomer2.setId(2l);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(mockCustomer1);
        customerList.add(mockCustomer2);
        when(customerService.findAll(any())).thenReturn(new PageImpl<>(customerList, PageRequest.of(0,2),2));

        MvcResult result = mockMvc.perform(get("/api/customers/")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(customerList)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
       // assertEquals(EXPEXTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);

    }
    @Test
    void deleteCustomerTest() throws Exception {
        Customer mockCustomer = createCustomer();
        mockCustomer.setId(1l);

        doNothing().when(customerService).delete(anyLong());

        MvcResult result = mockMvc.perform(delete("/api/customers/1")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockCustomer)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }

}
