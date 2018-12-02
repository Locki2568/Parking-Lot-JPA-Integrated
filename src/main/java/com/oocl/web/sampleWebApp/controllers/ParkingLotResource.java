package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/parkingboys")
public class ParkingBoyResource {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;
    //@Autowired
    //private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<ParkingBoyResponse[]> getAll() {
        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
                .map(ParkingBoyResponse::create)
                .toArray(ParkingBoyResponse[]::new);
        return ResponseEntity.ok(parkingBoys);
    }

    //@Transactional
    @PostMapping(produces = {"application/json"},consumes = {"application/json"})
    public ResponseEntity<ParkingBoyResponse> appendParkingboy(@RequestBody ParkingBoy parkingBoy) {
        parkingBoyRepository.save(parkingBoy);
        parkingBoyRepository.flush();
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
