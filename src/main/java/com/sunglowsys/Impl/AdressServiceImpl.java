package com.sunglowsys.Impl;

import com.sunglowsys.domain.Address;
import com.sunglowsys.repository.AddressRepository;
import com.sunglowsys.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class AdressServiceImpl implements AddressService {
    private final Logger logger = LoggerFactory.getLogger(AdressServiceImpl.class);
    private final AddressRepository addressRepository;

    public AdressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address save(Address address) {
        logger.debug("Request to save address:{}",address);
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        logger.debug("Request to update address:{}",address);
        return addressRepository.save(address);
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        logger.debug("Request to findAll address:{}",pageable);
        return addressRepository.findAll(pageable);
    }

    @Override
    public Optional<Address> findOne(Long id) {
        logger.debug("Request to findOne address:{}",id);
        return addressRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Request to delete address:{}",id);
        addressRepository.deleteById(id);

    }
}
