package com.sajiman.myroomrent.Dtos;

public class RoomInfoDto {
    private int roomInfoId;
    private int roomId;
    private double roomCharge;
    private double electricityInitialUnit;
    private double electricityFinalUnit;

    public RoomInfoDto(int roomID, double roomCharge, double electricityInitialUnit, double electricityFinalUnit) {
        this.roomId = roomID;
        this.roomCharge = roomCharge;
        this.electricityInitialUnit = electricityInitialUnit;
        this.electricityFinalUnit = electricityFinalUnit;

    }

    public RoomInfoDto(int roomID) {
        this.roomId = roomID;
    }

    public RoomInfoDto() {

    }

    public int getRoomInfoId() {
        return roomInfoId;
    }

    public void setRoomInfoId(int roomInfoId) {
        this.roomInfoId = roomInfoId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(double roomCharge) {
        this.roomCharge = roomCharge;
    }

    public double getElectricityInitialUnit() {
        return electricityInitialUnit;
    }

    public void setElectricityInitialUnit(double electricityInitialUnit) {
        this.electricityInitialUnit = electricityInitialUnit;
    }

    public double getElectricityFinalUnit() {
        return electricityFinalUnit;
    }

    public void setElectricityFinalUnit(double electricityFinalUnit) {
        this.electricityFinalUnit = electricityFinalUnit;
    }

    @Override
    public String toString() {
        return "RoomInfoDto{" +
                "roomInfoId=" + roomInfoId +
                ", roomId=" + roomId +
                ", roomCharge=" + roomCharge +
                ", electricityInitialUnit=" + electricityInitialUnit +
                ", electricityFinalUnit=" + electricityFinalUnit +
                '}';
    }
}
