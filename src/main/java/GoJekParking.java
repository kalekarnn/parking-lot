import common.InputModeType;
import common.ValidationException;
import service.factory.inputmode.InputMode;
import service.factory.inputmode.InputModeFactory;

public class GoJekParking {

    public static void main(String[] args) throws ValidationException {

        InputMode inputMode = null;
        InputModeFactory inputModeFactory = new InputModeFactory();

        if (args.length == 0) {
            inputMode = inputModeFactory.getInputMode(InputModeType.INTERACTIVE_INPUT);
        } else if (args.length == 1) {
            inputMode = inputModeFactory.getInputMode(InputModeType.FILE_INPUT);
            inputMode.setProperties(args[0]);
        } else {
            System.out.println("Invalid number of arguments.");
            System.exit(1);
        }

        inputMode.collectInput();
    }
}
