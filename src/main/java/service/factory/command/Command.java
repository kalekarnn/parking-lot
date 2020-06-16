package service.factory.command;

import common.ValidationException;
import service.ParkingService;

public abstract class Command {

    private String parameters;
    private ParkingService parkingService;

    public Command() {
        parkingService = ParkingService.getParkingService();
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public ParkingService getParkingService() {
        return parkingService;
    }

    public abstract void validate() throws ValidationException;

    public abstract void execute();

}
