package com.sunglowsys.resource;

import com.sunglowsys.domain.Address;
import com.sunglowsys.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressResource {
    private final Logger logger = LoggerFactory.getLogger(AddressResource.class);
    private final AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping("/addresses")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        logger.debug("Request to save Address:{}",address);
        Address result = addressService.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/addresses")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        logger.debug("Request to update Address");
        if (address.getId()==null) {
            throw new RuntimeException("id must not be null");
        }
        Address result = addressService.update(address);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }
    @GetMapping("/addresses")
    public ResponseEntity<Page<Address>> findAll(Pageable pageable) {
        logger.debug("Request to findAll Address:{}",pageable);
        Page<Address> result = addressService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/addresses/{id}")
    public ResponseEntity<Optional<Address>> findOne(@PathVariable Long id) {
        logger.debug("Request to findOne Address:{}",id);
        Optional<Address> result = addressService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("addresses/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.debug("Request to delete Address:{}",id);
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
