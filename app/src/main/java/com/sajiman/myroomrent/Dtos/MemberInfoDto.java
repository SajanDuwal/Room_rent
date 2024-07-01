package com.sajiman.myroomrent.Dtos;

/**
 * Created by HP on 2/1/2018.
 */

public class MemberInfoDto {
    private int id;
    private String name;
    private String address;
    private String contact;
    private String imagePath;

    public MemberInfoDto(int mainId, String name, String address, String contact) {
        this.id = mainId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public MemberInfoDto() {

    }

    public MemberInfoDto(String name) {
        this.name = name;
    }


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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "MemberInfoDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
