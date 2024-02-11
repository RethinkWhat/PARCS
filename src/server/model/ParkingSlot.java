package server.model;

public class ParkingSlot {
    /**
     * The type of parking slot.
     * C for car, M for motorcycle
     */
    private char type;
    /**
     * The reservation status of the parking slot.
     * True if taken, false if not.
     */
    private boolean status;

    /**
     * Constructs a ParkingSlot object with default values.
     */
    public ParkingSlot() {
        type = 'c';
        status = false;
    }

    /**
     * Constructs a ParkingSlot object with a specified type and status.
     * @param type The specified parking slot type.
     * @param status The specified status.
     */
    public ParkingSlot(char type, boolean status) {
        this.type = type;
        this.status = status;
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

    /**
     * Retrieves the current reservation status of the parking slot.
     * @return The current reservation status of the parking slot.
     */
    public boolean isReserved() {
        return status;
    }

    /**
     * Mutates the new status of the parking slot.
     * @param status The new status of the parking slot.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    // TODO: toString method
}
