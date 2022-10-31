package com.sunglowsys.Impl;

import com.sunglowsys.domain.HotelInventory;
import com.sunglowsys.repository.HotelInventoryRepository;
import com.sunglowsys.service.HotelInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class HotelInventoryImpl implements HotelInventoryService {
    private final Logger logger = LoggerFactory.getLogger(HotelInventoryImpl.class);
    private final HotelInventoryRepository hotelInventoryRepository;

    public HotelInventoryImpl(HotelInventoryRepository hotelInventoryRepository) {
        this.hotelInventoryRepository = hotelInventoryRepository;
    }

    @Override
    public HotelInventory save(HotelInventory hotelInventory) {
        logger.debug("REST Request to save the HotelInventory:{}",hotelInventory);
        return hotelInventoryRepository.save(hotelInventory);
    }

    @Override
    public HotelInventory update(HotelInventory hotelInventory) {
        logger.debug("REST Request to update the HotelInventory:{}",hotelInventory);
        return hotelInventoryRepository.save(hotelInventory);
    }

    @Override
    public Page<HotelInventory> findAll(Pageable pageable) {
        logger.debug("REST Request to findAll the HotelInventory:{}",pageable);
        return hotelInventoryRepository.findAll(pageable);
    }

    @Override
    public Optional<HotelInventory> findOne(Long id) {
        logger.debug("REST Request to findOne the HotelInventory:{}",id);
        return hotelInventoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        logger.debug("REST Request to delete the HotelInventory:{}",id);
        hotelInventoryRepository.deleteById(id);

    }
}
