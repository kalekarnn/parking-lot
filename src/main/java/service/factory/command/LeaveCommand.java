package service.factory.command;

import common.ErrorCodes;
import common.ValidationException;

import static common.Constants.SPACE;

public class LeaveCommand extends Command {

    public void validate() throws ValidationException {
        if (getParameters().split(SPACE).length != 2) {
            throw new ValidationException(ErrorCodes.INCORRECT_NO_OF_ARGS.toString());
        }
    }

    public void execute() {
        getParkingService().leave(Integer.parseInt(getParameters().split(SPACE)[1]));
    }
}
