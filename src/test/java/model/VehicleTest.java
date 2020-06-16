package model;

import org.junit.Assert;
import org.junit.Test;

public class VehicleTest {

    @Test
    public void testGetter() {
        String regNo = "regNo";
        String colour = "colour";
        Vehicle vehicle = new Vehicle(regNo, colour);

        Assert.assertEquals(regNo, vehicle.getRegNo());
        Assert.assertEquals(colour, vehicle.getColour());
    }

}