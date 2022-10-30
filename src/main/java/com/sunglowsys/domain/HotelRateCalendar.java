package com.sunglowsys.domain;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "hotel_rate_calendar")
public class HotelRateCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "single_occupancy")
    private Integer singleOccupancy;
    @Column(name = "double_occupancy")
    private Integer doubleOccupancy;
    @Column(name = "extra_child_price")
    private Integer extraChildPrice;
    @Column(name = "extra_adult_price")
    private Integer extraAdultPrice;
    @Column(name = "applicable_days")
    private Integer applicableDays;

    public HotelRateCalendar() {
    }

    public HotelRateCalendar(Integer singleOccupancy, Integer doubleOccupancy, Integer extraChildPrice, Integer extraAdultPrice, Integer applicableDays) {
        this.singleOccupancy = singleOccupancy;
        this.doubleOccupancy = doubleOccupancy;
        this.extraChildPrice = extraChildPrice;
        this.extraAdultPrice = extraAdultPrice;
        this.applicableDays = applicableDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSingleOccupancy() {
        return singleOccupancy;
    }

    public void setSingleOccupancy(Integer singleOccupancy) {
        this.singleOccupancy = singleOccupancy;
    }

    public Integer getDoubleOccupancy() {
        return doubleOccupancy;
    }

    public void setDoubleOccupancy(Integer doubleOccupancy) {
        this.doubleOccupancy = doubleOccupancy;
    }

    public Integer getExtraChildPrice() {
        return extraChildPrice;
    }

    public void setExtraChildPrice(Integer extraChildPrice) {
        this.extraChildPrice = extraChildPrice;
    }

    public Integer getExtraAdultPrice() {
        return extraAdultPrice;
    }

    public void setExtraAdultPrice(Integer extraAdultPrice) {
        this.extraAdultPrice = extraAdultPrice;
    }

    public Integer getApplicableDays() {
        return applicableDays;
    }

    public void setApplicableDays(Integer applicableDays) {
        this.applicableDays = applicableDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRateCalendar that = (HotelRateCalendar) o;
        return Objects.equals(id, that.id) && Objects.equals(singleOccupancy, that.singleOccupancy) && Objects.equals(doubleOccupancy, that.doubleOccupancy) && Objects.equals(extraChildPrice, that.extraChildPrice) && Objects.equals(extraAdultPrice, that.extraAdultPrice) && Objects.equals(applicableDays, that.applicableDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, singleOccupancy, doubleOccupancy, extraChildPrice, extraAdultPrice, applicableDays);
    }

    @Override
    public String toString() {
        return "HotelRateCalendar{" +
                "id=" + id +
                ", singleOccupancy=" + singleOccupancy +
                ", doubleOccupancy=" + doubleOccupancy +
                ", extraChildPrice=" + extraChildPrice +
                ", extraAdultPrice=" + extraAdultPrice +
                ", applicableDays=" + applicableDays +
                '}';
    }
}
