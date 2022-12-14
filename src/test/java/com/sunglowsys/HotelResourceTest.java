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
    private static Hotel createHotel2() {
        Hotel hotel = new Hotel();
        hotel.setCode("102");
        hotel.setName("raj");
        hotel.setHotelType("3 Star");
        hotel.setEmail("raj10496@gmail.com");
        hotel.setMobile("8433175578");

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
    @Test
    void getHotelTest() throws Exception{
        Hotel mockHotel = createHotel();
        mockHotel.setId(1l);

        when(hotelService.findOne(anyLong())).thenReturn(Optional.of(mockHotel));

        MvcResult result = mockMvc.perform(get("/api/hotels/1").accept(TestUtil.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getAllHotelTest() throws Exception {
        Hotel mockHotel1 = createHotel();
        mockHotel1.setId(1l);
        Hotel mockHotel2 = createHotel2();
        mockHotel2.setId(2l);
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(mockHotel1);
        hotelList.add(mockHotel2);

        when(hotelService.findAll(any())).thenReturn(new PageImpl<>(hotelList, PageRequest.of(0,2),2));

        MvcResult result = mockMvc.perform(get("/api/hotels").contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(hotelList)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        //assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);

    }
    @Test
    void deleteHotelTest() throws Exception {
        Hotel mockHotel = createHotel();
        mockHotel.setId(1l);

        doNothing().when(hotelService).delete(anyLong());

        MvcResult result = mockMvc.perform(delete("/api/hotels/1")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotel)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }
}
