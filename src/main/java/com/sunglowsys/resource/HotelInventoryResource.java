package com.sunglowsys.resource;

import com.sunglowsys.domain.HotelInventory;
import com.sunglowsys.service.HotelInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @PutMapping("/hotelInventory")
    public ResponseEntity<HotelInventory> update(@RequestBody HotelInventory hotelInventory) {
        logger.debug("REST Request to update the HotelInventory:{}",hotelInventory);
        if (hotelInventory.getId()==null) {
            throw new RuntimeException("id must not be null");
        }
        HotelInventory result = hotelInventoryService.update(hotelInventory);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/hotelInventory")
    public ResponseEntity<Page<HotelInventory>> findAll(Pageable pageable) {
        logger.debug("REST Request to findAll the HotelInventory:{}",pageable);
        Page<HotelInventory> result = hotelInventoryService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/hotelInventory/{id}")
    public ResponseEntity<Optional<HotelInventory>> findOne(@PathVariable Long id) {
        logger.debug("REST Request to findOne the HotelInventory:{}",id);
        Optional<HotelInventory> result = hotelInventoryService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/hotelInventory{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.debug("REST Request to delete the HotelInventory:{}",id);
        hotelInventoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
