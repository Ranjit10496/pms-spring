package com.sunglowsys;

import com.sunglowsys.domain.HotelInventory;
import com.sunglowsys.resource.HotelInventoryResource;
import com.sunglowsys.service.HotelInventoryService;
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
@WebMvcTest(value = HotelInventoryResource.class)
public class HotelInventoryResourceTest {

    private static String EXEPECTED_RESULT = "{\"id\" : 1,"+
            "\"totalInventory\" :\"10\"}";


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelInventoryService hotelInventoryService;

    private static HotelInventory createHotelInventory() {
        HotelInventory hotelInventory = new HotelInventory();
        hotelInventory.setTotalInventory("10");
        return hotelInventory;
    }
    private static HotelInventory createHotelInventory2() {
        HotelInventory hotelInventory = new HotelInventory();
        hotelInventory.setTotalInventory("20");
        return hotelInventory;
    }
    @Test
    void createHotelInventoryTest() throws Exception {
        HotelInventory mockHotelInventory = createHotelInventory();
        mockHotelInventory.setId(1L);
        when(hotelInventoryService.save(any())).thenReturn(mockHotelInventory);
        MvcResult result = mockMvc.perform(post("/api/hotelInventory")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelInventory)))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(),result.getResponse().getStatus());
        assertEquals(EXEPECTED_RESULT,result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);

    }
    @Test
    void updateHotelInventoryTest() throws Exception {
        HotelInventory mockHotelInventory = createHotelInventory();
        mockHotelInventory.setId(1L);
        when(hotelInventoryService.update(any())).thenReturn(mockHotelInventory);
        MvcResult result = mockMvc.perform(put("/api/hotelInventory")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockHotelInventory)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXEPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getHotelInventoryTest() throws Exception {
        HotelInventory mockHotelInventory = createHotelInventory();
        mockHotelInventory.setId(1L);
        when(hotelInventoryService.findOne(anyLong())).thenReturn(Optional.of(mockHotelInventory));

        MvcResult result = mockMvc.perform(get("/api/hotelInventory/1")
                .accept(TestUtil.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXEPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getAllHotelInventoryTest() throws Exception {
        HotelInventory mockHotelInventory1 = createHotelInventory();
        mockHotelInventory1.setId(1L);
        HotelInventory mockHotelInventory2 = createHotelInventory2();
        mockHotelInventory2.setId(2L);
        List<HotelInventory> hotelInventoryList = new ArrayList<>();
        hotelInventoryList.add(mockHotelInventory1);
        hotelInventoryList.add(mockHotelInventory2);

        when(hotelInventoryService.findAll(any())).thenReturn(new PageImpl<>(hotelInventoryList, PageRequest.of(0,2),2));

        MvcResult result = mockMvc.perform(get("/api/hotelInventory")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(hotelInventoryList)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
      //  assertEquals(EXEPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void deleteHotelInventoryTest() throws Exception {
        HotelInventory mockHotelInventory = createHotelInventory();
        mockHotelInventory.setId(1L);
        doNothing().when(hotelInventoryService).delete(anyLong());
        MvcResult result = mockMvc.perform(delete("/api/hotelInventory/1")
                        .contentType(TestUtil.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(mockHotelInventory)))
                        .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());

    }
}
