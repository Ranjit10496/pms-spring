package com.sunglowsys.resource;

import com.sunglowsys.domain.RoomType;
import com.sunglowsys.service.RoomTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoomTypeResource {
    private final Logger logger = LoggerFactory.getLogger(RoomTypeResource.class);
    private final RoomTypeService roomTypeService;

    public RoomTypeResource(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }
    @PostMapping("/roomType")
    public ResponseEntity<RoomType> save(@RequestBody RoomType roomType) {
        logger.debug("REST Request to save the RoomType:{}",roomType);
        RoomType result = roomTypeService.save(roomType);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
