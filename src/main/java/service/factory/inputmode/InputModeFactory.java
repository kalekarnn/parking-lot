package service.factory.inputmode;

import common.ErrorCodes;
import common.InputModeType;
import common.ValidationException;

public class InputModeFactory {

    public InputMode getInputMode(InputModeType inputModeType) throws ValidationException {
        switch (inputModeType) {
            case FILE_INPUT:
                return new FileInputMode();
            case INTERACTIVE_INPUT:
                return new InteractiveInputMode();
            default:
                throw new ValidationException(ErrorCodes.INVALID_INPUT_MODE.toString());
        }
    }
}
