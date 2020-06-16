package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingSlotTest {

    @Test
    public void testGetterSetter() {
        Vehicle vehicle = new Vehicle();
        ParkingSlot parkingSlot = new ParkingSlot(true, vehicle);

        assertTrue(parkingSlot.isOccupied());
        assertEquals(vehicle, parkingSlot.getVehicle());
    }

}