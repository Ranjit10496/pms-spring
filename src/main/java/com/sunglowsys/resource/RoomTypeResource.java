package com.sunglowsys.resource;

import com.sunglowsys.domain.RoomType;
import com.sunglowsys.service.RoomTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/roomType")
    public ResponseEntity<RoomType> update(@RequestBody RoomType roomType) {
        logger.debug("REST Request to update the RoomType:{}",roomType);
        if (roomType.getId()==null) {
            throw new RuntimeException("id must not be null");
        }
        RoomType result = roomTypeService.update(roomType);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/roomType")
    public ResponseEntity<Page<RoomType>> findAll(Pageable pageable) {
        logger.debug("REST Request to findAll RoomType:{}",pageable);
        Page<RoomType> result = roomTypeService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
