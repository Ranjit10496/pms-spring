package com.sunglowsys.Impl;

import com.sunglowsys.domain.HotelBooking;
import com.sunglowsys.repository.HotelBookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class HotelBookingServiceImpl implements com.sunglowsys.service.HotelBookingService {
    private final Logger logger = LoggerFactory.getLogger(HotelBookingServiceImpl.class);
    private final HotelBookingRepository hotelBookingRepository;

    public HotelBookingServiceImpl(HotelBookingRepository hotelBookingRepository) {
        this.hotelBookingRepository = hotelBookingRepository;
    }

    @Override
    public HotelBooking save(HotelBooking hotelBooking) {
        logger.debug("Request save the HotelBooking:{}",hotelBooking);
        return hotelBookingRepository.save(hotelBooking);
    }

    @Override
    public HotelBooking update(HotelBooking hotelBooking) {
        logger.debug("Request update the HotelBooking:{}",hotelBooking);
        return hotelBookingRepository.save(hotelBooking);
    }

    @Override
    public Page<HotelBooking> findAll(Pageable pageable) {
        logger.debug("Request to findAll the HotelBooking:{}",pageable);
        return hotelBookingRepository.findAll(pageable);
    }

    @Override
    public Optional<HotelBooking> findOne(Long id) {
        logger.debug("Request to findOne the HotelBooking:{}",id);
        return hotelBookingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Request to delete the HotelBooking:{}",id);
        hotelBookingRepository.deleteById(id);

    }
}
