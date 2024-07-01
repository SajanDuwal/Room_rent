package com.sajiman.myroomrent.Dtos;

import android.os.Parcel;
import android.os.Parcelable;

public class RoomRentDto implements Parcelable {

    private int id;
    private String name;
    private String address;
    private String contact;

    private int roomId;
    private double roomCharge;
    private double electricityInitialUnit;
    private double electricityFinalUnit;

    private int rentClearedUptoMonth;
    private double paidRoomCharge;
    private double paidElectricityCharge;
    private String dateOfRentPaid;
    private Boolean status;


    public RoomRentDto(int id, String name, String address, String contact, int roomID, double roomCharge,
                       double electricityInitialUnit, double electricityFinalUnit,
                       int rentClearedUptoMonth, double paidRoomCharge,
                       double paidElectricityCharge, String dateOfRentPaid, Boolean status) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;

        this.roomId = roomID;
        this.roomCharge = roomCharge;
        this.electricityInitialUnit = electricityInitialUnit;
        this.electricityFinalUnit = electricityFinalUnit;

        this.rentClearedUptoMonth = rentClearedUptoMonth;
        this.paidRoomCharge = paidRoomCharge;
        this.paidElectricityCharge = paidElectricityCharge;
        this.dateOfRentPaid = dateOfRentPaid;
        this.status = status;

    }

    public RoomRentDto(int roomID) {
        this.roomId = roomID;
    }

    protected RoomRentDto(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        contact = in.readString();
        roomId = in.readInt();
        roomCharge = in.readDouble();
        electricityInitialUnit = in.readDouble();
        electricityFinalUnit = in.readDouble();
        rentClearedUptoMonth = in.readInt();
        paidRoomCharge = in.readDouble();
        paidElectricityCharge = in.readDouble();
        dateOfRentPaid = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
    }

    public static final Creator<RoomRentDto> CREATOR = new Creator<RoomRentDto>() {
        @Override
        public RoomRentDto createFromParcel(Parcel in) {
            return new RoomRentDto(in);
        }

        @Override
        public RoomRentDto[] newArray(int size) {
            return new RoomRentDto[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public int getRentClearedUptoMonth() {
        return rentClearedUptoMonth;
    }

    public void setRentClearedUptoMonth(int rentClearedUptoMonth) {
        this.rentClearedUptoMonth = rentClearedUptoMonth;
    }

    public double getPaidRoomCharge() {
        return paidRoomCharge;
    }

    public void setPaidRoomCharge(double paidRoomCharge) {
        this.paidRoomCharge = paidRoomCharge;
    }

    public double getPaidElectricityCharge() {
        return paidElectricityCharge;
    }

    public void setPaidElectricityCharge(double paidElectricityCharge) {
        this.paidElectricityCharge = paidElectricityCharge;
    }

    public String getDateOfRentPaid() {
        return dateOfRentPaid;
    }

    public void setDateOfRentPaid(String dateOfRentPaid) {
        this.dateOfRentPaid = dateOfRentPaid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoomRentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", roomId=" + roomId +
                ", roomCharge=" + roomCharge +
                ", electricityInitialUnit=" + electricityInitialUnit +
                ", electricityFinalUnit=" + electricityFinalUnit +
                ", rentClearedUptoMonth=" + rentClearedUptoMonth +
                ", paidRoomCharge=" + paidRoomCharge +
                ", paidElectricityCharge=" + paidElectricityCharge +
                ", dateOfRentPaid='" + dateOfRentPaid + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(contact);
        dest.writeInt(roomId);
        dest.writeDouble(roomCharge);
        dest.writeDouble(electricityInitialUnit);
        dest.writeDouble(electricityFinalUnit);
        dest.writeInt(rentClearedUptoMonth);
        dest.writeDouble(paidRoomCharge);
        dest.writeDouble(paidElectricityCharge);
        dest.writeString(dateOfRentPaid);
        dest.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
    }
}
