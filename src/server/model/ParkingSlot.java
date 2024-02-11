package server.model;

public class ParkingSlot {
    /**
     * The type of parking slot.
     * C for car, M for motorcycle
     */
    private char type;


    /**
     * Constructs a ParkingSlot object with default values.
     */
    public ParkingSlot() {
        type = 'c';
    }

    /**
     * Constructs a ParkingSlot object with a specified type and status.
     * @param type The specified parking slot type.
     * @param status The specified status.
     */
    public ParkingSlot(char type, boolean status) {
        this.type = type;
    }

    /**
     * Retrieves the current type of the parking slot.
     * @return The current type of the parking slot.
     */
    public char getType() {
        return type;
    }

    /**
     * Mutates the new type of the parking slot.
     * @param type The new type of the parking slot.
     */
    public void setType(char type) {
        this.type = type;
    }

}
