package com.sunglowsys;

import com.sunglowsys.domain.HotelBooking;
import com.sunglowsys.resource.HotelBookingResource;
import com.sunglowsys.service.HotelBookingService;
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
@WebMvcTest(value = HotelBookingResource.class)
public class HotelBookingResourceTest {

    private static String EXPEXTED_RESULT = "{\"id\" : 1," +
            "\"checkInDate\" : \"2022-12-25\"," +
            "\"checkOutDate\" : \"2022-12-30\"," +
            "\"totalGuest\" : 10," +
            "\"noOfNights\" : 4," +
            "\"bookingAmount\" : 200}";


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelBookingService hotelBookingService;

    private static HotelBooking createHotelBooking() {
        HotelBooking hotelBooking = new HotelBooking();
        hotelBooking.setCheckInDate("2022-12-25");
        hotelBooking.setCheckOutDate("2022-12-30");
        hotelBooking.setTotalGuest(10L);
        hotelBooking.setNoOfNights(4L);
        hotelBooking.setBookingAmount(200f);
        return hotelBooking;
    }
    private static HotelBooking createHotelBooking2() {
        HotelBooking hotelBooking = new HotelBooking();
        hotelBooking.setCheckInDate("2021-12-22");
        hotelBooking.setCheckOutDate("2021-12-21");
        hotelBooking.setTotalGuest(9L);
        hotelBooking.setNoOfNights(5L);
        hotelBooking.setBookingAmount(300f);
        return hotelBooking;
    }

    @Test
    void createHotelBookingTest() throws Exception {
        HotelBooking mockHotelBooking = createHotelBooking();
        mockHotelBooking.setId(1L);

        when(hotelBookingService.save(any())).thenReturn(mockHotelBooking);
        MvcResult result = mockMvc.perform(post("/api/hotelBookings")
                        .contentType(TestUtil.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(mockHotelBooking)))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT, result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
    }
    @Test
    void updateHotelBookingTest() throws Exception {
        HotelBooking mockHotelBooking = createHotelBooking();
        mockHotelBooking.setId(1L);

        when(hotelBookingService.update(any())).thenReturn(mockHotelBooking);
        MvcResult result = mockMvc.perform(put("/api/hotelBookings")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelBooking)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getHotelBookingTest() throws Exception {
        HotelBooking mockHotelBooking = createHotelBooking();
        mockHotelBooking.setId(1L);
        when(hotelBookingService.findOne(anyLong())).thenReturn(Optional.of(mockHotelBooking));
        MvcResult result = mockMvc.perform(get("/api/hotelBookings/1")
                .accept(TestUtil.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXPEXTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getAllHotelBookingTest() throws Exception {
        HotelBooking mockHotelBooking1 = createHotelBooking();
        mockHotelBooking1.setId(1L);
        HotelBooking mockHotelBooking2 = createHotelBooking2();
        mockHotelBooking2.setId(2L);
        List<HotelBooking> hotelBookingList = new ArrayList<>();
        hotelBookingList.add(mockHotelBooking1);
        hotelBookingList.add(mockHotelBooking2);

        when(hotelBookingService.findAll(any())).thenReturn(new PageImpl<>(hotelBookingList ,PageRequest.of(0,2),2));
        MvcResult result = mockMvc.perform(get("/api/hotelBookings")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(hotelBookingList)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
       // assertEquals(EXPEXTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void deleteHotelBookingTest() throws Exception {
        HotelBooking mockHotelBooking = createHotelBooking();
        mockHotelBooking.setId(1L);
        doNothing().when(hotelBookingService).delete(anyLong());
        MvcResult result = mockMvc.perform(delete("/api/hotelBookings/1").contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelBooking)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());

    }
}
