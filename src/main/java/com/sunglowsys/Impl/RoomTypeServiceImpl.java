package com.sunglowsys.Impl;

import com.sunglowsys.domain.RoomType;
import com.sunglowsys.repository.RoomTypeRepository;
import com.sunglowsys.service.RoomTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {
    private final Logger logger = LoggerFactory.getLogger(RoomTypeServiceImpl.class);
    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomType save(RoomType roomType) {
        logger.debug("REST Request to save the RoomType:{}",roomType);
        return roomTypeRepository.save(roomType);
    }

    @Override
    public RoomType update(RoomType roomType) {
        logger.debug("REST Request to update the RoomType:{}",roomType);
        return roomTypeRepository.save(roomType);
    }

    @Override
    public Page<RoomType> findAll(Pageable pageable) {
        logger.debug("REST Request to findAll the RoomType:{}",pageable);
        return roomTypeRepository.findAll(pageable);
    }
}
