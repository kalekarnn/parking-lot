package model;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void testGetterSetter() {
        ParkingLot parkingLot = new ParkingLot(5);
        Assert.assertEquals(5, parkingLot.getParkingSlots().size());
    }
}