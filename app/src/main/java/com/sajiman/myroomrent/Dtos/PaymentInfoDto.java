package com.sajiman.myroomrent.Dtos;

/**
 * Created by HP on 2/1/2018.
 */

public class PaymentInfoDto {
    private int paymentInfoId;
    private int rentClearedUptoMonth;
    private double paidRoomCharge;
    private double paidElectricityCharge;
    private String dateOfRentPaid;
    private Boolean status;

    public PaymentInfoDto(int rentClearedUptoMonth, double paidRoomCharge, double paidElectricityCharge, String dateOfRentCleared, Boolean status) {
        this.rentClearedUptoMonth = rentClearedUptoMonth;
        this.paidRoomCharge = paidRoomCharge;
        this.paidElectricityCharge = paidElectricityCharge;
        this.dateOfRentPaid = dateOfRentCleared;
        this.status = status;
    }

    public PaymentInfoDto() {

    }

    public PaymentInfoDto(int rentPaidUpto, Boolean status) {
        this.rentClearedUptoMonth = rentPaidUpto;
        this.status = status;
    }

    public int getPaymentInfoId() {
        return paymentInfoId;
    }

    public void setPaymentInfoId(int paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
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
        return "PaymentInfoDto{" +
                "paymentInfoId=" + paymentInfoId +
                ", rentClearedUptoMonth=" + rentClearedUptoMonth +
                ", paidRoomCharge=" + paidRoomCharge +
                ", paidElectricityCharge=" + paidElectricityCharge +
                ", dateOfRentPaid='" + dateOfRentPaid + '\'' +
                ", status=" + status +
                '}';
    }
}
