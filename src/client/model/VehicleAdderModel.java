package client.model;

/**
 * Template for VehicleAdderModel.
 * TODO: Documentation
 */
public class VehicleAdderModel {
    /**
     * The vehicle types
     */
    private String[] vehicleTypes;
    /**
     * The compiled data of user input to be sent to the server.
     */
    private Object userInput;

    /**
     * Reference to the client
     */
    private Client client;

    /**
     * Constructs an object of VehicleAdderModel with default values.
     */
    public VehicleAdderModel(Client client) {
        vehicleTypes = new String[]{"Select Type:", "Car", "Motorcycle"};
        this.client = client;
    }

    /**
     * Retrieves the current vehicle types.
     * @return The current vehicle types.
     */
    public String[] getVehicleTypes() {
        return vehicleTypes;
    }

    public boolean writeVehicle(String type, String model, String plateNumber) {
        client.openSocket();
        client.writeString("addVehicle");
        client.writeString(type);
        client.writeString(model);
        client.writeString(plateNumber);
        boolean vehicleAccepted = client.readString().equals("true");
        client.closeSocket();

        return vehicleAccepted;
    }

}
