package com.oocl.web.sampleWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.List;
import java.util.Objects;

public class ParkingBoyResponse {
    private String employeeId;

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    private List<ParkingLot> parkingLots;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public static ParkingBoyResponse create(String employeeId) {
        Objects.requireNonNull(employeeId);

        final ParkingBoyResponse response = new ParkingBoyResponse();
        response.setEmployeeId(employeeId);
        return response;
    }

    public static ParkingBoyResponse create(String employeeId, List<ParkingLot> parkingLots) {
        Objects.requireNonNull(employeeId);
        Objects.requireNonNull(parkingLots);

        final ParkingBoyResponse response = new ParkingBoyResponse();
        response.setEmployeeId(employeeId);
        response.setParkingLots(parkingLots);
        return response;
    }

    public static ParkingBoyResponse create(ParkingBoy entity) {
        if (entity.getParkingLotList().size() == 0) {
            return create(entity.getEmployeeId());
        }
        return create(entity.getEmployeeId(), entity.getParkingLotList());
    }

    @JsonIgnore
    public boolean isValid() {
        return employeeId != null;
    }
}
