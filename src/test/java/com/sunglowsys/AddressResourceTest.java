package com.sunglowsys;

import com.sunglowsys.domain.Address;
import com.sunglowsys.resource.AddressResource;
import com.sunglowsys.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AddressResource.class)
public class AddressResourceTest {

    private static String EXPEXTED_RESULT = "{\"id\": 1,"+
            "\"addressLine1\" : \"Delhi\","+
            "\"addressLine2\" : \"Rajiv\","+
            "\"city\" : \"New\","+
            "\"state\" : \"New Delhi\","+
            "\"country\" :\"India\","+
            "\"zipcode\" : \"204211\"}";


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;

    private static Address createAddress() {
        Address address = new Address();
        address.setAddressLine1("Delhi");
        address.setAddressLine2("Rajiv");
        address.setCity("New");
        address.setState("New Delhi");
        address.setCountry("India");
        address.setZipcode("204211");

        return address;
    }
    @Test
    void createAddressTest() throws Exception {
        Address mockAddress = createAddress();
        mockAddress.setId(1l);

        when(addressService.save(any())).thenReturn(mockAddress);
        MvcResult result = mockMvc.perform(post("/api/addresses")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockAddress)))
                .andReturn();

        assertEquals(HttpStatus.CREATED.value(),result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void updateAddressTest() throws Exception {
        Address mockAddress = createAddress();
        mockAddress.setId(1l);

        when(addressService.update(any())).thenReturn(mockAddress);
        MvcResult result = mockMvc.perform(put("/api/addresses")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockAddress)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }

}
