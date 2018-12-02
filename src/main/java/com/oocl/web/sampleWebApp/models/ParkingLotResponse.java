package com.oocl.web.sampleWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.Objects;

public class ParkingLotResponse {
    private String parkingLotID;
    private int capacity;
    private ParkingBoy parkingBoy;

    public String getParkingLotID() {
        return parkingLotID;
    }
    public int getCapacity(){ return capacity; }
    public ParkingBoy getParkingBoy() {return parkingBoy;}

    public void setEmployeeId(String parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public void setParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoy = parkingBoy;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static ParkingLotResponse create(String parkingLotID, int capacity, ParkingBoy parkingBoy) {
        Objects.requireNonNull(parkingLotID);
        Objects.requireNonNull(capacity);
        Objects.requireNonNull(parkingBoy);

        final ParkingLotResponse response = new ParkingLotResponse();
        response.setEmployeeId(parkingLotID);
        response.setCapacity(capacity);
        response.setParkingBoy(parkingBoy);
        return response;
    }

    public static ParkingLotResponse create(String parkingLotID, int capacity) {
        Objects.requireNonNull(parkingLotID);
        Objects.requireNonNull(capacity);

        final ParkingLotResponse response = new ParkingLotResponse();
        response.setEmployeeId(parkingLotID);
        response.setCapacity(capacity);
        return response;
    }

    public static ParkingLotResponse create(ParkingLot entity) {
        if (entity.getParkingBoy() == null) {
            return create(entity.getParkingLotID(), entity.getCapacity());
        }
        return create(entity.getParkingLotID(), entity.getCapacity(), entity.getParkingBoy());
    }

    @JsonIgnore
    public boolean isValid() {
        return parkingLotID != null;
    }
}
