package com.oocl.web.sampleWebApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "parking_boy_id")
//    private String parkingBoyID;

    @Column(name = "parking_lot_id", length = 12, unique = true, nullable = false)
    private String parkingLotID;

    @Min(1)
    @Max(100)
    @Column(name = "capacity",  nullable = false)
    private int capacity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parkingBoy_id")
    private ParkingBoy parkingBoy;

    protected ParkingLot() {}

    public ParkingLot(String parkingLotID, int capacity){
        this.parkingLotID = parkingLotID;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

//    public String getParkingBoyID() {
//        return parkingBoyID;
//    }
//
//    public void setParkingBoyID(String parkingBoyID) {
//        this.parkingBoyID = parkingBoyID;
//    }

    public String getParkingLotID() {
        return parkingLotID;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingBoy getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoy = parkingBoy;
    }
}
