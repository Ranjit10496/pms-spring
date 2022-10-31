package com.sunglowsys.resource;

import com.sunglowsys.domain.HotelInventoryCalendar;
import com.sunglowsys.domain.HotelRateCalendar;
import com.sunglowsys.service.HotelRateCalendarService;
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
public class HotelRateCalendarResource {
    private final Logger logger = LoggerFactory.getLogger(HotelBookingResource.class);
    private final HotelRateCalendarService hotelRateCalendarService;

    public HotelRateCalendarResource(HotelRateCalendarService hotelRateCalendarService) {
        this.hotelRateCalendarService = hotelRateCalendarService;
    }
    @PostMapping("/hotelRateCalendars")
    public ResponseEntity<HotelRateCalendar> save(@RequestBody HotelRateCalendar hotelRateCalendar) {
        logger.debug("Request to save the HotelRateCalendar:{}",hotelRateCalendar);
        HotelRateCalendar result = hotelRateCalendarService.save(hotelRateCalendar);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/hotelRateCalendars")
    public ResponseEntity<HotelRateCalendar> update(@RequestBody HotelRateCalendar hotelRateCalendar) {
        logger.debug("Request to update the HotelRateCalendar:{}",hotelRateCalendar);
        if (hotelRateCalendar.getId()==null) {
            throw new RuntimeException("id must not be null");
        }
        HotelRateCalendar result = hotelRateCalendarService.update(hotelRateCalendar);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/hotelRateCalendars")
    public ResponseEntity<Page<HotelRateCalendar>> findAll(Pageable pageable) {
        logger.debug("Request to findAll the HotelRateCalendar:{}",pageable);
        Page<HotelRateCalendar> result = hotelRateCalendarService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/hotelRateCalendars/{id}")
    public ResponseEntity<Optional<HotelRateCalendar>> findOne(@PathVariable Long id) {
        logger.debug("Request to findOne the HotelRateCalendar:{}",id);
        Optional<HotelRateCalendar> result = hotelRateCalendarService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/hotelRateCalendars/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.debug("Request to delete the HotelRateCalendar:{}",id);
        hotelRateCalendarService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
