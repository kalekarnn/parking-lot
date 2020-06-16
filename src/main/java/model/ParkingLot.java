package model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSlot> parkingSlots;

    public ParkingLot(int size) {
        this.parkingSlots = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.parkingSlots.add(new ParkingSlot(false, new Vehicle()));
        }
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }
}
