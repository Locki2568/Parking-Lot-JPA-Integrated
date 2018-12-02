package com.oocl.web.sampleWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.Objects;

public class ParkingLotResponse {
    private String parkingLotID;
    private String parkingBoyID;
    private int capacity;

    public String getParkingLotID() {
        return parkingLotID;
    }
    public int getCapacity(){ return capacity; }
    public String getParkingBoyID() {return parkingBoyID;}


    public void setEmployeeId(String parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public void setParkingBoyID(String parkingBoyID) {
        this.parkingBoyID = parkingBoyID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static ParkingLotResponse create(String parkingLotID, int capacity, String parkingBoyID) {
        Objects.requireNonNull(parkingLotID);
        Objects.requireNonNull(capacity);
        Objects.requireNonNull(parkingBoyID);

        final ParkingLotResponse response = new ParkingLotResponse();
        response.setEmployeeId(parkingLotID);
        response.setCapacity(capacity);
        response.setParkingBoyID(parkingBoyID);
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
        if (entity.getParkingBoyID() == null) {
            return create(entity.getParkingLotID(), entity.getCapacity());
        }
        return create(entity.getParkingLotID(), entity.getCapacity(), entity.getParkingBoyID());
    }

    @JsonIgnore
    public boolean isValid() {
        return parkingLotID != null;
    }
}
