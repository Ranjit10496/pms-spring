package com.sunglowsys;

import com.sunglowsys.domain.HotelInventoryCalendar;
import com.sunglowsys.resource.HotelInventoryCalendarResource;
import com.sunglowsys.service.HotelInventoryCalendarService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = HotelInventoryCalendarResource.class)
public class HotelInventoryCalendarResourceTest {

    private static String EXPECTED_RESULT = "{\"id\" : 1,"+
            "\"available\" : 10,"+
            "\"sold\" : 5,"+
            "\"block\" : 5}";



    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelInventoryCalendarService hotelInventoryCalendarService;


    private static HotelInventoryCalendar createHotelInventoryCalendar() {
        HotelInventoryCalendar hotelInventoryCalendar = new HotelInventoryCalendar();
        hotelInventoryCalendar.setAvailable(10L);
        hotelInventoryCalendar.setSold(5L);
        hotelInventoryCalendar.setBlock(5L);
        return hotelInventoryCalendar;
    }
    private static HotelInventoryCalendar createHotelInventoryCalendar2() {
        HotelInventoryCalendar hotelInventoryCalendar = new HotelInventoryCalendar();
        hotelInventoryCalendar.setAvailable(20L);
        hotelInventoryCalendar.setSold(5L);
        hotelInventoryCalendar.setBlock(15L);
        return hotelInventoryCalendar;
    }
    @Test
    void createHotelInventoryCalendarTest() throws Exception {
        HotelInventoryCalendar mockHotelInventoryCalendar = createHotelInventoryCalendar();
        mockHotelInventoryCalendar.setId(1L);
        when(hotelInventoryCalendarService.save(any())).thenReturn(mockHotelInventoryCalendar);
        MvcResult result = mockMvc.perform(post("/api/hotelInventoryCalendars")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelInventoryCalendar)))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(),result.getResponse().getStatus());
        assertEquals(EXPECTED_RESULT,result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
    }@Test
    void updateHotelInventoryCalendarTest() throws Exception {
        HotelInventoryCalendar mockHotelInventoryCalendar = createHotelInventoryCalendar();
        mockHotelInventoryCalendar.setId(1L);
        when(hotelInventoryCalendarService.update(any())).thenReturn(mockHotelInventoryCalendar);
        MvcResult result = mockMvc.perform(put("/api/hotelInventoryCalendars")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelInventoryCalendar)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);

    }
    @Test
    void getHotelInventoryCalendarTest() throws Exception {
        HotelInventoryCalendar mockHotelInventoryCalendar = createHotelInventoryCalendar();
        mockHotelInventoryCalendar.setId(1L);
        when(hotelInventoryCalendarService.findOne(anyLong())).thenReturn(Optional.of(mockHotelInventoryCalendar));
        MvcResult result = mockMvc.perform(get("/api/hotelInventoryCalendars/1")
                .accept(TestUtil.APPLICATION_JSON))
                        .andReturn();

        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getAllHotelInventoryCalendarsTest() throws Exception {
        HotelInventoryCalendar mockHotelInventoryCalendar1 = createHotelInventoryCalendar();
        mockHotelInventoryCalendar1.setId(1L);
        HotelInventoryCalendar mockHotelInventoryCalnedar2 = createHotelInventoryCalendar2();
        mockHotelInventoryCalnedar2.setId(2L);
        List<HotelInventoryCalendar> hotelInventoryCalendarList = new ArrayList<>();
        hotelInventoryCalendarList.add(mockHotelInventoryCalendar1);
        hotelInventoryCalendarList.add(mockHotelInventoryCalnedar2);

        when(hotelInventoryCalendarService.findAll(any())).thenReturn(new PageImpl<>(hotelInventoryCalendarList, PageRequest.of(0,2),2));
        MvcResult result = mockMvc.perform(get("/api/hotelInventoryCalendars")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(hotelInventoryCalendarList)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        //assertEquals(EXPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void deleteHotelInventoryCalendarTest() throws Exception {
        HotelInventoryCalendar mockHotelInventoryCalendar = createHotelInventoryCalendar();
        mockHotelInventoryCalendar.setId(1L);
        doNothing().when(hotelInventoryCalendarService).delete(anyLong());
        MvcResult result = mockMvc.perform(delete("/api/hotelInventoryCalendars/1")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelInventoryCalendar)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }


}
