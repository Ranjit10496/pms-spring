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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
