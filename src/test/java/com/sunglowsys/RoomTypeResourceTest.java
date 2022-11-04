package com.sunglowsys;

import com.sunglowsys.domain.RoomType;
import com.sunglowsys.resource.RoomTypeResource;
import com.sunglowsys.service.RoomTypeService;
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
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RoomTypeResource.class)
public class RoomTypeResourceTest {

    private static String EXPECTED_RESULT = "{\"id\" : 1,"+
            "\"name\" : \"AC\"}";


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoomTypeService roomTypeService;

    private static RoomType createRoomType() {
        RoomType roomType = new RoomType();
        roomType.setName("AC");
        return roomType;
    }
    private static RoomType createRoomType2() {
        RoomType roomType = new RoomType();
        roomType.setName("BC");
        return roomType;
    }
    @Test
    void createRoomTypeTest() throws Exception {
        RoomType mockRoomType = createRoomType();
        mockRoomType.setId(1L);
        when(roomTypeService.save(any())).thenReturn(mockRoomType);
        MvcResult result = mockMvc.perform(post("/api/roomType")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockRoomType)))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(),result.getResponse().getStatus());
        assertEquals(EXPECTED_RESULT,result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);

    }
    @Test
    void updateRoomTypeTest() throws Exception {
        RoomType mockRoomType = createRoomType();
        mockRoomType.setId(1L);
        when(roomTypeService.update(any())).thenReturn(mockRoomType);
        MvcResult result = mockMvc.perform(put("/api/roomType")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockRoomType)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);

    }
    @Test
    void getRoomTypeTest() throws Exception {
        RoomType mockRoomType = createRoomType();
        mockRoomType.setId(1L);
        when(roomTypeService.findOne(anyLong())).thenReturn(Optional.of(mockRoomType));
        MvcResult result = mockMvc.perform(get("/api/roomType/1")
                .accept(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockRoomType)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals(EXPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void getAllRoomTypeTest() throws Exception {
        RoomType mockRoomType1 = createRoomType();
        mockRoomType1.setId(1L);
        RoomType mockRoomType2 = createRoomType2();
        mockRoomType2.setId(2L);
        List<RoomType> roomTypeList = new ArrayList<>();
        roomTypeList.add(mockRoomType1);
        roomTypeList.add(mockRoomType2);

        when(roomTypeService.findAll(any())).thenReturn(new PageImpl<>(roomTypeList, PageRequest.of(0,2),2));

        MvcResult result = mockMvc.perform(get("/api/roomType")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(roomTypeList)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
       // assertEquals(EXPECTED_RESULT,result.getResponse().getContentAsString(),JSONCompareMode.LENIENT);
    }
    @Test
    void deleteRoomTypeTest() throws Exception {
        RoomType mockRoomType = createRoomType();
        mockRoomType.setId(1L);
        doNothing().when(roomTypeService).delete(anyLong());
        MvcResult result = mockMvc.perform(delete("/api/roomType/1")
                .contentType(TestUtil.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(mockRoomType)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }
}
