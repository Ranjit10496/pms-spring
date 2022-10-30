package com.sunglowsys.service;

import com.sunglowsys.domain.HotelRateCalendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface HotelRateCalendarService {
    HotelRateCalendar save(HotelRateCalendar hotelRateCalendar);
    HotelRateCalendar update(HotelRateCalendar hotelRateCalendar);
    Page<HotelRateCalendar> findAll(Pageable pageable);
    Optional<HotelRateCalendar> findOne(Long id);
    void delete(Long id);
}
