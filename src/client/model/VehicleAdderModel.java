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
     * Constructs an object of VehicleAdderModel with default values.
     */
    public VehicleAdderModel() {
        vehicleTypes = new String[]{"Select Type:", "Car", "Motorcycle"};
    }

    /**
     * Retrieves the current vehicle types.
     * @return The current vehicle types.
     */
    public String[] getVehicleTypes() {
        return vehicleTypes;
    }

    public void writeVehicle(String type, String model, String plateNumber) {}

}
