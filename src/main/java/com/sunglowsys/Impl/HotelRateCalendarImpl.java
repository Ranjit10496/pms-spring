package com.sunglowsys.Impl;

import com.sunglowsys.domain.HotelRateCalendar;
import com.sunglowsys.repository.HotelRateCalendarRepository;
import com.sunglowsys.service.HotelRateCalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class HotelRateCalendarImpl implements HotelRateCalendarService {
    private final Logger logger = LoggerFactory.getLogger(HotelRateCalendarImpl.class);
    private final HotelRateCalendarRepository hotelRateCalendarRepository;

    public HotelRateCalendarImpl(HotelRateCalendarRepository hotelRateCalendarRepository) {
        this.hotelRateCalendarRepository = hotelRateCalendarRepository;
    }

    @Override
    public HotelRateCalendar save(HotelRateCalendar hotelRateCalendar) {
        logger.debug("Request to save the HotelRateCalendar:{}",hotelRateCalendar);
        return hotelRateCalendarRepository.save(hotelRateCalendar);
    }

    @Override
    public HotelRateCalendar update(HotelRateCalendar hotelRateCalendar) {
        logger.debug("Request to update the HotelRateCalendar");
        return hotelRateCalendarRepository.save(hotelRateCalendar);
    }

    @Override
    public Page<HotelRateCalendar> findAll(Pageable pageable) {
        logger.debug("Request to findAll HotelRateCalendar:{}",pageable);
        return hotelRateCalendarRepository.findAll(pageable);
    }

    @Override
    public Optional<HotelRateCalendar> findOne(Long id) {
        logger.debug("Request to findOne HotelRateCalendar:{}",id);
        return hotelRateCalendarRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Request to delete the HotelRateCalendar:{}",id);
        hotelRateCalendarRepository.deleteById(id);

    }
}
