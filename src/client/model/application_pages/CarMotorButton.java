package client.model.application_pages;

import javax.swing.*;

public class CarMotorButton extends JButton {

    private String identifier;

    public CarMotorButton(String identifier) {
        this.identifier = identifier;
    }

    public boolean isCar() {
        return (identifier.contains("C"));
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
