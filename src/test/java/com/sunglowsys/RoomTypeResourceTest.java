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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


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
}
