package service.factory.command;

import common.ErrorCodes;
import common.ValidationException;
import model.Vehicle;

import static common.Constants.SPACE;

public class ParkCommand extends Command {

    public void validate() throws ValidationException {
        if (getParameters().split(SPACE).length != 3) {
            throw new ValidationException(ErrorCodes.INCORRECT_NO_OF_ARGS.toString());
        }
    }

    public void execute() {
        Vehicle vehicle = new Vehicle(getParameters().split(SPACE)[1], getParameters().split(SPACE)[2]);
        getParkingService().park(vehicle);
    }
}
