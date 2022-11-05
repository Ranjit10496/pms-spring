package com.sunglowsys;

import com.sunglowsys.domain.HotelRateCalendar;
import com.sunglowsys.resource.HotelRateCalendarResource;
import com.sunglowsys.service.HotelRateCalendarService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = HotelRateCalendarResource.class)
public class HotelRateCalendarResourceTest {

    private static String EXEPCTED_RESULT = "{\"id\" : 1,"+
            "\"singleOccupancy\" : 5,"+
            "\"doubleOccupancy\" : 10,"+
            "\"extraChildPrice\" : 100,"+
            "\"extraAdultPrice\" : 200,"+
            "\"applicableDays\" : 10}";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelRateCalendarService hotelRateCalendarService;

    private static HotelRateCalendar createHotelRateCalendar() {
        HotelRateCalendar hotelRateCalendar = new HotelRateCalendar();
        hotelRateCalendar.setSingleOccupancy(5);
        hotelRateCalendar.setDoubleOccupancy(10);
        hotelRateCalendar.setExtraChildPrice(100);
        hotelRateCalendar.setExtraAdultPrice(200);
        hotelRateCalendar.setApplicableDays(10);

        return hotelRateCalendar;

    }
    private static HotelRateCalendar createHotelRateCalendar2() {
        HotelRateCalendar hotelRateCalendar = new HotelRateCalendar();
        hotelRateCalendar.setSingleOccupancy(10);
        hotelRateCalendar.setDoubleOccupancy(20);
        hotelRateCalendar.setExtraChildPrice(200);
        hotelRateCalendar.setExtraAdultPrice(400);
        hotelRateCalendar.setApplicableDays(20);

        return hotelRateCalendar;
    }
    @Test
    void createHotelRateCalendarTest() throws Exception {
        HotelRateCalendar mockHotelRateCalendar = createHotelRateCalendar();
        mockHotelRateCalendar.setId(1L);

        when(hotelRateCalendarService.save(any())).thenReturn(mockHotelRateCalendar);
        MvcResult result = mockMvc.perform(post("/api/hotelRateCalendars")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelRateCalendar)))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(),result.getResponse().getStatus());
        assertEquals(EXEPCTED_RESULT,result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
    }
    @Test
    void updateHotelRateCalendarTest() throws Exception {
        HotelRateCalendar mockHotelRateCalendar = createHotelRateCalendar();
        mockHotelRateCalendar.setId(1L);
        when(hotelRateCalendarService.update(any())).thenReturn(mockHotelRateCalendar);
        MvcResult result = mockMvc.perform(put("/api/hotelRateCalendars")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelRateCalendar)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXEPCTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getHotelRateCalendarTest() throws Exception {
        HotelRateCalendar mockHotelRateCalendar = createHotelRateCalendar();
        mockHotelRateCalendar.setId(1L);
        when(hotelRateCalendarService.findOne(anyLong())).thenReturn(Optional.of(mockHotelRateCalendar));
        MvcResult result = mockMvc.perform(get("/api/hotelRateCalendars/1")
                .accept(TestUtil.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXEPCTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getAllHotelRateCalendarTest() throws Exception {
        HotelRateCalendar mockHotelRateCalendar1 = createHotelRateCalendar();
        mockHotelRateCalendar1.setId(1L);
        HotelRateCalendar mockHotelRateCalendar2 = createHotelRateCalendar2();
        mockHotelRateCalendar2.setId(2L);
        List<HotelRateCalendar> hotelRateCalendarList = new ArrayList<>();
        hotelRateCalendarList.add(mockHotelRateCalendar1);
        hotelRateCalendarList.add(mockHotelRateCalendar2);

        when(hotelRateCalendarService.findAll(any())).thenReturn(new PageImpl<>(hotelRateCalendarList, PageRequest.of(0,2),2));
        MvcResult result = mockMvc.perform(get("/api/hotelRateCalendars")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(hotelRateCalendarList)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
       // assertEquals(EXEPCTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);

    }
    @Test
    void deleteHotelRateCalendarTest() throws Exception {
        HotelRateCalendar mockHotelRateCalendar = createHotelRateCalendar();
        mockHotelRateCalendar.setId(1L);
        doNothing().when(hotelRateCalendarService).delete(anyLong());
        MvcResult result = mockMvc.perform(delete("/api/hotelRateCalendars/1")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelRateCalendar)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }

}
