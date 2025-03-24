

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    private JFrame frame;
    
    // Smart Light Components
    private JLabel lightStatus;
    
    // Smart Thermostat Components
    private JLabel thermostatStatus;
    private JTextField tempInput;
    
    // Smart Lock Components
    private JLabel lockStatus;
    
    public GUI() { 
        frame = new JFrame("Smart Home System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new GridLayout(4, 1, 10, 10)); // Separate sections evenly
    
        
        // ===================== Smart Light Panel =====================
        JPanel lightPanel = new JPanel();
        lightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Smart Light"));
        lightPanel.setLayout(new GridLayout(2, 1)); // Label on top, buttons below
        
        lightStatus = new JLabel("Light is now OFF", SwingConstants.CENTER);
        JPanel lightButtonPanel = new JPanel(new FlowLayout());
        JButton turnOn = new JButton("Turn ON");
        JButton turnOff = new JButton("Turn OFF");

        turnOn.addActionListener(e -> updateLightStatus(true));
        turnOff.addActionListener(e -> updateLightStatus(false));

        lightButtonPanel.add(turnOn);
        lightButtonPanel.add(turnOff);

        lightPanel.add(lightStatus);
        lightPanel.add(lightButtonPanel);
        frame.add(lightPanel);

        // ===================== Smart Thermostat Panel =====================
        JPanel thermostatPanel = new JPanel();
        thermostatPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Smart Thermostat"));
        thermostatPanel.setLayout(new GridLayout(2, 1));

        thermostatStatus = new JLabel("Temperature: 22°C", SwingConstants.CENTER);
        JPanel thermostatControlPanel = new JPanel(new FlowLayout());
        tempInput = new JTextField(5);
        JButton setTemp = new JButton("Set Temperature");

        setTemp.addActionListener(e -> updateThermostat());

        thermostatControlPanel.add(tempInput);
        thermostatControlPanel.add(setTemp);

        thermostatPanel.add(thermostatStatus);
        thermostatPanel.add(thermostatControlPanel);
        frame.add(thermostatPanel);

        // ===================== Smart Lock Panel =====================
        JPanel lockPanel = new JPanel();
        lockPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Smart Lock"));
        lockPanel.setLayout(new GridLayout(2, 1));

        lockStatus = new JLabel("Lock: LOCKED", SwingConstants.CENTER);
        JPanel lockButtonPanel = new JPanel(new FlowLayout());
        JButton lockButton = new JButton("Lock");
        JButton unlockButton = new JButton("Unlock");

        lockButton.addActionListener(e -> updateLockStatus(true));
        unlockButton.addActionListener(e -> updateLockStatus(false));

        lockButtonPanel.add(lockButton);
        lockButtonPanel.add(unlockButton);

        lockPanel.add(lockStatus);
        lockPanel.add(lockButtonPanel);
        frame.add(lockPanel);

        // ===================== Mode Control Panel =====================
        JPanel modePanel = new JPanel();
        modePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Modes"));
        modePanel.setLayout(new GridLayout(2, 1));

        JLabel modeLabel = new JLabel("Select Mode", SwingConstants.CENTER);
        JPanel modeButtonPanel = new JPanel(new FlowLayout());
        JButton sleepMode = new JButton("Sleep Mode");
        JButton vacationMode = new JButton("Vacation Mode");

        sleepMode.addActionListener(e -> applyMode("Sleep"));
        vacationMode.addActionListener(e -> applyMode("Vacation"));

        modeButtonPanel.add(sleepMode);
        modeButtonPanel.add(vacationMode);

        modePanel.add(modeLabel);
        modePanel.add(modeButtonPanel);
        frame.add(modePanel);

        // ===================== Finalize UI =====================
        frame.setVisible(true);
    }

    // Light Status Update
    private void updateLightStatus(boolean isOn) {
        lightStatus.setText("Light is now " + (isOn ? "ON" : "OFF"));
        JOptionPane.showMessageDialog(frame, "Light is now " + (isOn ? "ON" : "OFF"), "Smart Home System", JOptionPane.INFORMATION_MESSAGE);
    }

    // Thermostat Update
    private void updateThermostat() {
        try {
            int temp = Integer.parseInt(tempInput.getText());
            thermostatStatus.setText("Temperature: " + temp + "°C");
            JOptionPane.showMessageDialog(frame, "Thermostat set to " + temp + "°C", "Smart Home System", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lock Status Update
    private void updateLockStatus(boolean isLocked) {
        lockStatus.setText("Lock: " + (isLocked ? "LOCKED" : "UNLOCKED"));
        JOptionPane.showMessageDialog(frame, "Lock is now " + (isLocked ? "LOCKED" : "UNLOCKED"), "Smart Home System", JOptionPane.INFORMATION_MESSAGE);
    }

    // Apply Modes
    private void applyMode(String mode) {
        JOptionPane.showMessageDialog(frame, mode + " Mode Activated", "Smart Home System", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
