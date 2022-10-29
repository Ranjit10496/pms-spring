package com.sunglowsys.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "hotel_booking")
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false,name = "check_in_date")
    private Date checkInDate;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false,name = "check_out_date")
    private Date checkOutDate;
    @Column(name = "total_guest")
    private Long totalGuest;
    @Column(name = "no_of_nights")
    private Long noOfNights;
    @Column(name = "booking_amount")
    private float BookingAmount;

    public HotelBooking() {
    }

    public HotelBooking(Date checkInDate, Date checkOutDate, Long totalGuest, Long noOfNights, float bookingAmount) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalGuest = totalGuest;
        this.noOfNights = noOfNights;
        BookingAmount = bookingAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Long getTotalGuest() {
        return totalGuest;
    }

    public void setTotalGuest(Long totalGuest) {
        this.totalGuest = totalGuest;
    }

    public Long getNoOfNights() {
        return noOfNights;
    }

    public void setNoOfNights(Long noOfNights) {
        this.noOfNights = noOfNights;
    }

    public float getBookingAmount() {
        return BookingAmount;
    }

    public void setBookingAmount(float bookingAmount) {
        BookingAmount = bookingAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelBooking that = (HotelBooking) o;
        return Float.compare(that.BookingAmount, BookingAmount) == 0 && Objects.equals(id, that.id) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate) && Objects.equals(totalGuest, that.totalGuest) && Objects.equals(noOfNights, that.noOfNights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkInDate, checkOutDate, totalGuest, noOfNights, BookingAmount);
    }

    @Override
    public String toString() {
        return "HotelBooking{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalGuest=" + totalGuest +
                ", noOfNights=" + noOfNights +
                ", BookingAmount=" + BookingAmount +
                '}';
    }
}
