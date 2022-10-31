package com.sunglowsys.resource;

import com.sunglowsys.domain.HotelInventory;
import com.sunglowsys.service.HotelInventoryService;
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
public class HotelInventoryResource {
    private final Logger logger = LoggerFactory.getLogger(HotelInventoryResource.class);
    private final HotelInventoryService hotelInventoryService;

    public HotelInventoryResource(HotelInventoryService hotelInventoryService) {
        this.hotelInventoryService = hotelInventoryService;
    }
    @PostMapping("/hotelInventory")
    public ResponseEntity<HotelInventory> save(@RequestBody HotelInventory hotelInventory) {
        logger.debug("REST Request to save the HotelInventory:{}",hotelInventory);
        HotelInventory result = hotelInventoryService.save(hotelInventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
