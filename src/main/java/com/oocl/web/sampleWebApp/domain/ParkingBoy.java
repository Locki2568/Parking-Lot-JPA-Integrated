package com.oocl.web.sampleWebApp.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_boy")
public class ParkingBoy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", length = 12, unique = true, nullable = false)
    private String employeeId;

    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "parkingBoy", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public void addParkingLot(ParkingLot parkingLot){
        this.parkingLotList.add(parkingLot);
        parkingLot.setParkingBoy(this);
    }

    public void removeParkingLot(ParkingLot parkingLot){
        this.parkingLotList.remove(parkingLot);
        parkingLot.setParkingBoy(null);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    protected ParkingBoy() {}

    public ParkingBoy(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }
}

