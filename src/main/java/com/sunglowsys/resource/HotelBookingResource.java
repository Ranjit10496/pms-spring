package com.sunglowsys.resource;

import com.sunglowsys.domain.HotelBooking;
import com.sunglowsys.service.HotelBookingService;
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
public class HotelBookingResource {
    private final Logger logger = LoggerFactory.getLogger(HotelBookingResource.class);
    private final HotelBookingService hotelBookingService;

    public HotelBookingResource(HotelBookingService hotelBookingService) {
        this.hotelBookingService = hotelBookingService;
    }
    @PostMapping("/hotelBookings")
    public ResponseEntity<HotelBooking> save(@RequestBody HotelBooking hotelBooking) {
        logger.debug("Request to save the HotelBooking:{}",hotelBooking);
        HotelBooking result = hotelBookingService.save(hotelBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/hotelBookings")
    public ResponseEntity<HotelBooking> update(@RequestBody HotelBooking hotelBooking) {
        logger.debug("Request to update the HotelBooking:{}",hotelBooking);
        if (hotelBooking.getId()==null) {
            throw new RuntimeException("Id must not be null");
        }
        HotelBooking result = hotelBookingService.update(hotelBooking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/hotelBookings")
    public ResponseEntity<Page<HotelBooking>> findAll(Pageable pageable) {
        logger.debug("Request to findAll the HotelBooking:{}",pageable);
        Page<HotelBooking> result = hotelBookingService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/hotelBookings/{id}")
    public ResponseEntity<Optional<HotelBooking>> findOne(@PathVariable Long id) {
        logger.debug("Request to findOne the HotelBooking:{}",id);
        Optional<HotelBooking> result = hotelBookingService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/hotelBookings/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.debug("Request to delete the HotelBooking:{}",id);
        hotelBookingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
