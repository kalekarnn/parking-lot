package model;

public class ParkingSlot {
    private boolean occupied;
    private Vehicle vehicle;

    public ParkingSlot(boolean occupied, Vehicle vehicle) {
        this.occupied = occupied;
        this.vehicle = vehicle;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
