package com.sunglowsys.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hotel_inventory_calendar")
public class HotelInventoryCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "available")
    private Long available;
    @Column(name = "sold")
    private Long sold;
    @Column(name = "block")
    private Long block;

    public HotelInventoryCalendar() {
    }

    public HotelInventoryCalendar(Long available, Long sold, Long block) {
        this.available = available;
        this.sold = sold;
        this.block = block;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public Long getBlock() {
        return block;
    }

    public void setBlock(Long block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelInventoryCalendar that = (HotelInventoryCalendar) o;
        return Objects.equals(id, that.id) && Objects.equals(available, that.available) && Objects.equals(sold, that.sold) && Objects.equals(block, that.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, available, sold, block);
    }

    @Override
    public String toString() {
        return "HotelInventoryCalendar{" +
                "id=" + id +
                ", available=" + available +
                ", sold=" + sold +
                ", block=" + block +
                '}';
    }
}
