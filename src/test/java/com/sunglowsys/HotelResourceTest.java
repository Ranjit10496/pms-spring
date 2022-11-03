package com.sunglowsys;


import com.sunglowsys.domain.Hotel;
import com.sunglowsys.resource.HotelResource;
import com.sunglowsys.service.HotelService;
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
@WebMvcTest(value = HotelResource.class)
public class HotelResourceTest {

    private static String EXPEXTED_RESULT  = "{\"id\": 1,"+

            " \"code\" : \"101\"," +
            " \"name\" : \"Taj\"," +
            "\"hotelType\" : \"5 Star\"," +
            "\"email\" : \"taj543@gmail.com\"," +
            "\"mobile\" : \"9696988970\"}";



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    private static Hotel createHotel() {
        Hotel hotel = new Hotel();
        hotel.setCode("101");
        hotel.setName("Taj");
        hotel.setHotelType("5 Star");
        hotel.setEmail("taj543@gmail.com");
        hotel.setMobile("9696988970");

        return hotel;

    }
    

    @Test
    void createHotelTest() throws Exception{
        Hotel mockHotel = createHotel();
        mockHotel.setId(1L);

        when(hotelService.save(any())).thenReturn(mockHotel);

        MvcResult result = mockMvc.perform(post("/api/hotels").contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotel)))
                .andReturn();

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);

    }
    @Test
    void updateHotelTest() throws Exception{
        Hotel mockHotel = createHotel();
        mockHotel.setId(1l);

        when(hotelService.update(any())).thenReturn(mockHotel);

        MvcResult result = mockMvc.perform(put("/api/hotels").contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotel)))
                .andReturn();

        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
}
