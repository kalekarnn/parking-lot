package service;

import common.ValidationException;
import dao.ParkingDao;
import model.Vehicle;

public class ParkingService {
    private static ParkingService parkingService = null;

    private ParkingDao parkingDao;

    private ParkingService() {
        this.parkingDao = ParkingDao.getParkingDao();
    }

    public static ParkingService getParkingService() {
        if (parkingService == null)
            parkingService = new ParkingService();
        return parkingService;
    }

    public void createParkingLot(int size) {
        parkingDao.createParkingLot(size);
    }

    public boolean park(Vehicle vehicle) {
        try {
            return parkingDao.park(vehicle);
        } catch (ValidationException e) {
            System.out.println(e);
        }
        return false;
    }

    public void showStatus() {
        try {
            parkingDao.showStatus();
        } catch (ValidationException e) {
            System.out.println(e);
        }
    }

    public boolean leave(int slotNo) {
        try {
            return parkingDao.leave(slotNo);
        } catch (ValidationException e) {
            System.out.println(e);
        }
        return false;
    }

    public int slotNoByRegNo(String regNo) {
        try {
            return parkingDao.slotNoByRegNo(regNo);
        } catch (ValidationException e) {
            System.out.println("Not found");
        }
        return -1;
    }

    public String slotNosOfCarsWithColour(String colour) {
        try {
            return parkingDao.slotNosOfCarsWithColour(colour);
        } catch (ValidationException e) {
            System.out.println("Not found");
        }
        return "";
    }

    public String regNosOfCarsWithColour(String colour) {
        try {
            return parkingDao.regNosOfCarsWithColour(colour);
        } catch (ValidationException e) {
            System.out.println("Not found");
        }
        return "";
    }
}
