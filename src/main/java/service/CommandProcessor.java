package service;

import common.ValidationException;
import service.factory.command.Command;

public class CommandProcessor {

    public void process(Command command) throws ValidationException {
        command.validate();
        command.execute();
    }
}
