package service.factory.inputmode;

import common.CommandType;
import common.ValidationException;
import service.CommandProcessor;
import service.factory.command.Command;
import service.factory.command.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static common.Constants.SPACE;

public class InteractiveInputMode extends InputMode {
    public void collectInput() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        CommandFactory commandFactory = new CommandFactory();
        CommandProcessor commandProcessor = new CommandProcessor();

        while (true) {
            String strCommand = null;
            try {
                strCommand = bufferRead.readLine();
                if (strCommand.equals("exit"))
                    break;
                String[] commandLine = strCommand.split(SPACE, 2);
                Command command = commandFactory.getCommand(CommandType.getTypeByName(commandLine[0]));
                command.setParameters(strCommand);
                commandProcessor.process(command);
            } catch (IOException e) {
                System.out.println(e);
            } catch (ValidationException e) {
                System.out.println(e);
            }
        }
    }

    public void setProperties(String... arg) {

    }
}
