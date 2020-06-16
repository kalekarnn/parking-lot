package model;

public class Vehicle {
    private String regNo;
    private String colour;

    public Vehicle() {
    }

    public Vehicle(String regNo, String colour) {
        this.regNo = regNo;
        this.colour = colour;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getColour() {
        return colour;
    }
}
