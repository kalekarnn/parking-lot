package dao;

import common.ValidationException;
import model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ParkingDaoTest {

    @InjectMocks
    ParkingDao parkingDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createParkingLot() {
        parkingDao.createParkingLot(5);
    }

    @Test
    public void park() throws ValidationException {
        Vehicle vehicle = new Vehicle("reg", "clr");
        parkingDao.createParkingLot(2);
        assertTrue(parkingDao.park(vehicle));
        assertTrue(parkingDao.park(vehicle));
        assertFalse(parkingDao.park(vehicle));
    }

    @Test(expected = ValidationException.class)
    public void park_before_init_exception() throws ValidationException {
        Vehicle vehicle = new Vehicle("reg", "clr");
        parkingDao.park(vehicle);
    }

    @Test
    public void showStatus() throws ValidationException {
        Vehicle vehicle = new Vehicle("reg", "clr");
        parkingDao.createParkingLot(1);
        parkingDao.park(vehicle);
        parkingDao.showStatus();
    }

    @Test(expected = ValidationException.class)
    public void showStatus_before_init_exception() throws ValidationException {
        parkingDao.showStatus();
    }

    @Test
    public void leave() throws ValidationException {
        Vehicle vehicle = new Vehicle("reg", "clr");
        parkingDao.createParkingLot(2);
        parkingDao.park(vehicle);
        parkingDao.park(vehicle);
        assertTrue(parkingDao.leave(1));
        assertFalse(parkingDao.leave(20));
    }

    @Test(expected = ValidationException.class)
    public void leave_before_init_exception() throws ValidationException {
        parkingDao.leave(2);
    }

    @Test
    public void slotNoByRegNo() throws ValidationException {
        Vehicle vehicle1 = new Vehicle("reg1", "clr1");
        Vehicle vehicle2 = new Vehicle("reg2", "clr2");
        parkingDao.createParkingLot(2);
        parkingDao.park(vehicle1);
        parkingDao.park(vehicle2);
        assertEquals(1, parkingDao.slotNoByRegNo("reg1"));
        assertEquals(2, parkingDao.slotNoByRegNo("reg2"));
    }

    @Test(expected = ValidationException.class)
    public void slotNoByRegNo_before_init_exception() throws ValidationException {
        parkingDao.slotNoByRegNo("123");
    }

    @Test(expected = ValidationException.class)
    public void slotNoBy_IncorrectRegNo_exception() throws ValidationException {
        Vehicle vehicle = new Vehicle("reg1", "clr1");
        parkingDao.createParkingLot(2);
        parkingDao.park(vehicle);
        parkingDao.slotNoByRegNo("reg");
    }

    @Test
    public void slotNosOfCarsWithColour() throws ValidationException {
        Vehicle vehicle1 = new Vehicle("reg1", "clr");
        Vehicle vehicle2 = new Vehicle("reg2", "clr2");
        Vehicle vehicle3 = new Vehicle("reg3", "clr");
        parkingDao.createParkingLot(3);
        parkingDao.park(vehicle1);
        parkingDao.park(vehicle2);
        parkingDao.park(vehicle3);
        assertEquals("1, 3", parkingDao.slotNosOfCarsWithColour("clr"));
    }

    @Test(expected = ValidationException.class)
    public void slotNosOfCarsWithColour_before_init_exception() throws ValidationException {
        parkingDao.slotNosOfCarsWithColour("clr");
    }

    @Test(expected = ValidationException.class)
    public void slotNosOfCarsWith_IncorrectColour_exception() throws ValidationException {
        Vehicle vehicle = new Vehicle("reg1", "clr1");
        parkingDao.createParkingLot(3);
        parkingDao.park(vehicle);
        parkingDao.slotNosOfCarsWithColour("clr");
    }

    @Test
    public void regNosOfCarsWithColour() throws ValidationException {
        Vehicle vehicle1 = new Vehicle("reg1", "clr");
        Vehicle vehicle2 = new Vehicle("reg2", "clr2");
        Vehicle vehicle3 = new Vehicle("reg3", "clr");
        parkingDao.createParkingLot(3);
        parkingDao.park(vehicle1);
        parkingDao.park(vehicle2);
        parkingDao.park(vehicle3);
        assertEquals("reg1, reg3", parkingDao.regNosOfCarsWithColour("clr"));
    }

    @Test(expected = ValidationException.class)
    public void regNosOfCarsWithColour_before_init_exception() throws ValidationException {
        parkingDao.regNosOfCarsWithColour("clr");
    }

    @Test(expected = ValidationException.class)
    public void regNosOfCarsWith_IncorrectColour_exception() throws ValidationException {
        Vehicle vehicle = new Vehicle("reg1", "clr1");
        parkingDao.createParkingLot(3);
        parkingDao.park(vehicle);
        parkingDao.regNosOfCarsWithColour("clr");
    }
}