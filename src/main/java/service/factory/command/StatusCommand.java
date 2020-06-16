package service.factory.command;

import common.ErrorCodes;
import common.ValidationException;

import static common.Constants.SPACE;

public class StatusCommand extends Command {
    public void validate() throws ValidationException {
        if (getParameters().split(SPACE).length != 1) {
            throw new ValidationException(ErrorCodes.INCORRECT_NO_OF_ARGS.toString());
        }
    }

    public void execute() {
        getParkingService().showStatus();
    }
}
