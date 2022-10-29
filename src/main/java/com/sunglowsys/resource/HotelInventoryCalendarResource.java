package com.sunglowsys.resource;

import com.sunglowsys.domain.HotelInventoryCalendar;
import com.sunglowsys.service.HotelInventoryCalendarService;
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
public class HotelInventoryCalendarResource {
    private final Logger logger = LoggerFactory.getLogger(HotelInventoryCalendarResource.class);
    private final HotelInventoryCalendarService hotelInventoryCalendarService;

    public HotelInventoryCalendarResource(HotelInventoryCalendarService hotelInventoryCalendarService) {
        this.hotelInventoryCalendarService = hotelInventoryCalendarService;
    }
    @PostMapping("/hotelInventoryCalendars")
    public ResponseEntity<HotelInventoryCalendar> save(@RequestBody HotelInventoryCalendar hotelInventoryCalendar) {
        logger.debug("Request to save the HotelInventoryCalendar:{}",hotelInventoryCalendar);
        HotelInventoryCalendar result = hotelInventoryCalendarService.save(hotelInventoryCalendar);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/hotelInventoryCalendars")
    public ResponseEntity<HotelInventoryCalendar> update(@RequestBody HotelInventoryCalendar hotelInventoryCalendar) {
        logger.debug("Request to update HotelInventoryCalendar:{}",hotelInventoryCalendar);
        if (hotelInventoryCalendar.getId()==null) {
            throw new RuntimeException("id must not be null");
        }
        HotelInventoryCalendar result = hotelInventoryCalendarService.update(hotelInventoryCalendar);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/hotelInventoryCalendars")
    public ResponseEntity<Page<HotelInventoryCalendar>> findAll(Pageable pageable) {
        logger.debug("Request to findAll HotelInventoryCalendar:{}",pageable);
        Page<HotelInventoryCalendar> result = hotelInventoryCalendarService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/hotelInventoryCalendars/{id}")
    public ResponseEntity<Optional<HotelInventoryCalendar>> findOne(@PathVariable Long id) {
        logger.debug("Request to findOne the HotelInventoryCalendar{}",id);
        Optional<HotelInventoryCalendar> result = hotelInventoryCalendarService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/hotelInventoryCalendars/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.debug("Request to delete the HotelInventoryCalendar:{}",id);
        hotelInventoryCalendarService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
