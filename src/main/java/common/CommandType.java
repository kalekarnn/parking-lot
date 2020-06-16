package common;

public enum CommandType {
    CREATE_PARKING_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour"),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number");

    private String commandName;

    CommandType(String commandName) {
        this.commandName = commandName;
    }

    public static CommandType getTypeByName(String name) throws ValidationException {
        for (CommandType commandType : values()) {
            if (commandType.toString().equals(name)) {
                return commandType;
            }
        }
        throw new ValidationException(ErrorCodes.INVALID_COMMAND.toString());
    }

    @Override
    public String toString() {
        return this.commandName;
    }
}
