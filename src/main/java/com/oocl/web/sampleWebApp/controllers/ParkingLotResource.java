package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import com.oocl.web.sampleWebApp.models.ParkingLotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotResource {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    //@Autowired
    //private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<ParkingLotResponse[]> getAll() {
        final ParkingLotResponse[] parkingLots = parkingLotRepository.findAll().stream()
                .map(ParkingLotResponse::create)
                .toArray(ParkingLotResponse[]::new);
        return ResponseEntity.ok(parkingLots);
    }

    @PostMapping(produces = {"application/json"},consumes = {"application/json"})
    public ResponseEntity<ParkingLotResponse> appendParkingLot(@RequestBody ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
        parkingLotRepository.flush();
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
