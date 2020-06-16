package service.factory.command;

import common.CommandType;
import common.ErrorCodes;
import common.ValidationException;

public class CommandFactory {

    public Command getCommand(CommandType commandType) throws ValidationException {
        switch (commandType) {
            case PARK:
                return new ParkCommand();
            case LEAVE:
                return new LeaveCommand();
            case STATUS:
                return new StatusCommand();
            case CREATE_PARKING_LOT:
                return new CreateCommand();
            case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                return new RegNosOfCarsWithColourCommand();
            case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                return new SlotNosOfCarsWithColourCommand();
            case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                return new SlotNoByRegNo();
            default:
                throw new ValidationException(ErrorCodes.INVALID_COMMAND.toString());
        }
    }
}
