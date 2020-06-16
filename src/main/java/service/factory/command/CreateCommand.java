package service.factory.command;

import common.ErrorCodes;
import common.ValidationException;

import static common.Constants.SPACE;

public class CreateCommand extends Command {

    public void validate() throws ValidationException {
        if (getParameters().split(SPACE).length != 2) {
            throw new ValidationException(ErrorCodes.INCORRECT_NO_OF_ARGS.toString());
        }
    }

    public void execute() {
        getParkingService().createParkingLot(Integer.parseInt(getParameters().split(SPACE)[1]));
        System.out.println("Created a parking lot with " + Integer.parseInt(getParameters().split(SPACE)[1]) + " slots");
    }
}
