package com.scaler.parking_lot.models;

public class ParkingSpot extends BaseModel{

    private int number;
    private VehicleType supportedVehicleType;

    private ParkingSpotStatus status;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public VehicleType getSupportedVehicleType() {
        return supportedVehicleType;
    }

    public void setSupportedVehicleType(VehicleType supportedVehicleType) {
        this.supportedVehicleType = supportedVehicleType;
    }

    public ParkingSpotStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingSpotStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "number=" + number +
                ", supportedVehicleType=" + supportedVehicleType +
                ", status=" + status +
                '}';
    }
}
