package service;

import common.ValidationException;
import dao.ParkingDao;
import model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingServiceTest {
    @Mock
    ParkingDao parkingDao;

    @InjectMocks
    ParkingService parkingService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void park() throws ValidationException {
        Vehicle vehicle = new Vehicle("reg", "clr");
        Mockito.when(parkingDao.park(vehicle)).thenReturn(true);

        assertTrue(parkingService.park(vehicle));
    }

    @Test
    public void leave() throws ValidationException {
        Mockito.when(parkingDao.leave(1)).thenReturn(true);
        assertTrue(parkingService.leave(1));
    }

    @Test
    public void slotNoByRegNo() throws ValidationException {
        Mockito.when(parkingDao.slotNoByRegNo("regNo")).thenReturn(2);
        assertEquals(2, parkingService.slotNoByRegNo("regNo"));
    }

    @Test
    public void slotNosOfCarsWithColour() throws ValidationException {
        Mockito.when(parkingDao.slotNosOfCarsWithColour("clr")).thenReturn("1, 4");
        assertEquals("1, 4", parkingService.slotNosOfCarsWithColour("clr"));
    }

    @Test
    public void regNosOfCarsWithColour() throws ValidationException {
        Mockito.when(parkingDao.regNosOfCarsWithColour("clr")).thenReturn("1, 4");
        assertEquals("1, 4", parkingService.regNosOfCarsWithColour("clr"));
    }
}