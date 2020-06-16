package dao;

import common.ErrorCodes;
import common.ValidationException;
import model.ParkingLot;
import model.ParkingSlot;
import model.Vehicle;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingDao {
    public static final int BASE_INDEX = 1;
    private static ParkingDao parkingDao = null;

    private ParkingLot parkingLot;

    private ParkingDao() {
    }

    public static ParkingDao getParkingDao() {
        if (parkingDao == null)
            parkingDao = new ParkingDao();
        return parkingDao;
    }

    public void createParkingLot(int size) {
        this.parkingLot = new ParkingLot(size);
    }

    public boolean park(Vehicle vehicle) throws ValidationException {

        validateParkingLot();

        if (this.parkingLot.getParkingSlots().stream().allMatch(ParkingSlot::isOccupied)) {
            System.out.println("Sorry, parking lot is full");
            return false;
        } else {
            ParkingSlot freeParkingSlot = this.parkingLot.getParkingSlots().stream()
                    .filter(parkingSlot -> !parkingSlot.isOccupied())
                    .findFirst().get();

            freeParkingSlot.setOccupied(true);
            freeParkingSlot.setVehicle(vehicle);
            System.out.println("Allocated slot number: " + (this.parkingLot.getParkingSlots().indexOf(freeParkingSlot) + BASE_INDEX));
            return true;
        }
    }

    public void showStatus() throws ValidationException {

        validateParkingLot();

        List<ParkingSlot> parkingSlots = this.parkingLot.getParkingSlots();
        System.out.println("Slot No.    Registration No    Colour");
        for (int i = 0; i < parkingSlots.size(); i++) {
            if (parkingSlots.get(i).isOccupied())
                System.out.println((i + BASE_INDEX) + "           " + parkingSlots.get(i).getVehicle().getRegNo() + "      " + parkingSlots.get(i).getVehicle().getColour());
        }
    }

    public boolean leave(int slotNo) throws ValidationException {

        validateParkingLot();

        if (slotNo > this.parkingLot.getParkingSlots().size()) {
            System.out.println("Not found");
            return false;
        }

        ParkingSlot parkingSlot = this.parkingLot.getParkingSlots().get(slotNo - BASE_INDEX);
        if (!parkingSlot.isOccupied()) {
            throw new ValidationException(ErrorCodes.SLOT_IS_ALREADY_EMPTY.toString());
        } else {
            parkingSlot.setOccupied(false);
            parkingSlot.setVehicle(null);
            System.out.println("Slot number " + slotNo + " is free");
            return true;
        }
    }

    public int slotNoByRegNo(String regNo) throws ValidationException {

        validateParkingLot();

        ParkingSlot currentParkingSlot = this.parkingLot.getParkingSlots().stream()
                .filter(parkingSlot -> Objects.equals(parkingSlot.getVehicle().getRegNo(), regNo))
                .findFirst().orElseThrow(() -> new ValidationException(ErrorCodes.NOT_FOUND.toString()));
        int slotNo = this.parkingLot.getParkingSlots().indexOf(currentParkingSlot) + BASE_INDEX;
        System.out.println(slotNo);
        return slotNo;
    }

    public String slotNosOfCarsWithColour(String colour) throws ValidationException {

        validateParkingLot();

        List<ParkingSlot> parkingSlots = getParkingSlotsByVehicleColour(colour);

        if (parkingSlots.isEmpty()) {
            throw new ValidationException(ErrorCodes.NOT_FOUND.toString());
        }

        String result = parkingSlots.stream()
                .map(parkingSlot -> (this.parkingLot.getParkingSlots().indexOf(parkingSlot) + BASE_INDEX) + "")
                .collect(Collectors.joining(", "));
        System.out.println(result);
        return result;
    }

    public String regNosOfCarsWithColour(String colour) throws ValidationException {

        validateParkingLot();

        List<ParkingSlot> parkingSlots = getParkingSlotsByVehicleColour(colour);

        if (parkingSlots.isEmpty()) {
            throw new ValidationException(ErrorCodes.NOT_FOUND.toString());
        }

        String result = parkingSlots.stream()
                .map(parkingSlot -> parkingSlot.getVehicle().getRegNo() + "")
                .collect(Collectors.joining(", "));
        System.out.println(result);
        return result;
    }

    private List<ParkingSlot> getParkingSlotsByVehicleColour(String colour) {
        return this.parkingLot.getParkingSlots().stream()
                .filter(parkingSlot -> Objects.equals(parkingSlot.getVehicle().getColour(), colour))
                .collect(Collectors.toList());
    }

    private void validateParkingLot() throws ValidationException {
        if (this.parkingLot == null) {
            throw new ValidationException(ErrorCodes.NOT_FOUND.toString());
        }
    }
}
