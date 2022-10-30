package com.sunglowsys.repository;

import com.sunglowsys.domain.HotelRateCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRateCalendarRepository extends JpaRepository<HotelRateCalendar,Long> {
}
