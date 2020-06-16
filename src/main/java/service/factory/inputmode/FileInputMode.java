package service.factory.inputmode;

import common.CommandType;
import common.ValidationException;
import service.CommandProcessor;
import service.factory.command.Command;
import service.factory.command.CommandFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static common.Constants.SPACE;

public class FileInputMode extends InputMode {
    private String filePath;

    public void setProperties(String... arg) {
        this.filePath = arg[0];
    }

    public void collectInput() {

        CommandFactory commandFactory = new CommandFactory();
        CommandProcessor commandProcessor = new CommandProcessor();

        try {
            Files.readAllLines(Paths.get(filePath)).forEach(strCommand -> {
                String[] commandLine = strCommand.split(SPACE, 2);
                try {
                    Command command = commandFactory.getCommand(CommandType.getTypeByName(commandLine[0]));
                    command.setParameters(strCommand);
                    commandProcessor.process(command);
                } catch (ValidationException e) {
                    System.out.println(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
