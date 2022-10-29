package com.sunglowsys.Impl;

import com.sunglowsys.domain.HotelInventoryCalendar;
import com.sunglowsys.repository.HotelInventoryCalendarRepository;
import com.sunglowsys.service.HotelInventoryCalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class HotelInventoryCalendarServiceImpl implements HotelInventoryCalendarService {
    private final Logger logger = LoggerFactory.getLogger(HotelInventoryCalendarServiceImpl.class);
    private final HotelInventoryCalendarRepository hotelInventoryCalendarRepository;

    public HotelInventoryCalendarServiceImpl(HotelInventoryCalendarRepository hotelInventoryCalendarRepository) {
        this.hotelInventoryCalendarRepository = hotelInventoryCalendarRepository;
    }

    @Override
    public HotelInventoryCalendar save(HotelInventoryCalendar hotelInventoryCalendar) {
        logger.debug("Request to save the HotelInventoryCalendar:{}",hotelInventoryCalendar);
        return hotelInventoryCalendarRepository.save(hotelInventoryCalendar);
    }

    @Override
    public HotelInventoryCalendar update(HotelInventoryCalendar hotelInventoryCalendar) {
        logger.debug("Request to update the HotelInventoryCalendar:{}",hotelInventoryCalendar);
        return hotelInventoryCalendarRepository.save(hotelInventoryCalendar);
    }

    @Override
    public Page<HotelInventoryCalendar> findAll(Pageable pageable) {
        logger.debug("Request to findAll HotelInventoryCalendar:{}",pageable);
        return hotelInventoryCalendarRepository.findAll(pageable);
    }

    @Override
    public Optional<HotelInventoryCalendar> findOne(Long id) {
        logger.debug("Request to findOne HotelInventoryCalendar:{}",id);
        return hotelInventoryCalendarRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Request to delete the HotelInventoryCalendar:{}",id);
        hotelInventoryCalendarRepository.deleteById(id);

    }
}
