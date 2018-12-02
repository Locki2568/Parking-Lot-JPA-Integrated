package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parkingboys")
public class ParkingBoyResource {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @GetMapping
    public ResponseEntity<ParkingBoyResponse[]> getAll() {
        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
                .map(ParkingBoyResponse::create)
                .toArray(ParkingBoyResponse[]::new);
        return ResponseEntity.ok(parkingBoys);
    }

    @GetMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<ParkingBoyResponse> getByEmployeeID(@PathVariable String id) {
        final String employeeID = id;
        final ParkingBoy parkingBoyWithID = parkingBoyRepository.findByEmployeeId(employeeID);
        final ParkingBoyResponse parkingBoy = ParkingBoyResponse.create(parkingBoyWithID);
        return ResponseEntity.ok(parkingBoy);
    }

    @PostMapping(produces = {"application/json"},consumes = {"application/json"})
    public ResponseEntity<ParkingBoyResponse> appendParkingboy(@RequestBody ParkingBoy parkingBoy) {
        parkingBoyRepository.save(parkingBoy);
        parkingBoyRepository.flush();
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity associateParkingLotToParkingBoy(@RequestBody ParkingLot parkingLot, @PathVariable String id){
        String employeesID = id;
        ParkingBoy parkingBoyInDB = parkingBoyRepository.findByEmployeeId(employeesID);
        ParkingLot parkingLotInDB = parkingLotRepository.findByParkingLotID(parkingLot.getParkingLotID());
        parkingBoyInDB.addParkingLot(parkingLotInDB);
        parkingBoyRepository.flush();
        parkingLotRepository.flush();
        return new ResponseEntity(HttpStatus.OK);
    }
}
