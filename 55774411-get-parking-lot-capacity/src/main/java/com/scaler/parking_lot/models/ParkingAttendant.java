package com.scaler.parking_lot.models;

public class ParkingAttendant extends BaseModel{
    
    private String name;
    private String email;

    public ParkingAttendant(int id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

}
