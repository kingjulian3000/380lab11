package edu.ucalgary.oop;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GUI {
    private SmartHome smartHome;
    private SmartLight light;
    private SmartThermostat thermostat;
    private SmartLock lock;
    private JLabel lightLabel, thermostatLabel, lockLabel;
    private JTextField thermostatInput;

    public GUI() {
        light = new SmartLight();
        thermostat = new SmartThermostat();
        lock = new SmartLock();
        smartHome = new SmartHome().addDevice(light).addDevice(thermostat).addDevice(lock).build();

        JFrame frame = new JFrame("Smart Home System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(4, 1));

        JPanel lightPanel = new JPanel();
        lightLabel = new JLabel("Light is now " + (light.getState() ? "ON" : "OFF"));
        JButton lightOnButton = new JButton("Turn ON");
        JButton lightOffButton = new JButton("Turn OFF");
        lightOnButton.addActionListener(e -> updateLight(true));
        lightOffButton.addActionListener(e -> updateLight(false));
        lightPanel.add(new JLabel("Smart Light"));
        lightPanel.add(lightLabel);
        lightPanel.add(lightOnButton);
        lightPanel.add(lightOffButton);
        frame.add(lightPanel);

        JPanel thermostatPanel = new JPanel();
        thermostatLabel = new JLabel("Thermostat: " + thermostat.getState() + "°C");
        thermostatInput = new JTextField(5);
        JButton setTempButton = new JButton("Set Temperature");
        setTempButton.addActionListener(e -> updateThermostat());
        thermostatPanel.add(new JLabel("Smart Thermostat"));
        thermostatPanel.add(thermostatLabel);
        thermostatPanel.add(thermostatInput);
        thermostatPanel.add(setTempButton);
        frame.add(thermostatPanel);

        JPanel lockPanel = new JPanel();
        lockLabel = new JLabel("Lock: " + (lock.getState() ? "LOCKED" : "UNLOCKED"));
        JButton lockButton = new JButton("Lock");
        JButton unlockButton = new JButton("Unlock");
        lockButton.addActionListener(e -> updateLock(true));
        unlockButton.addActionListener(e -> updateLock(false));
        lockPanel.add(new JLabel("Smart Lock"));
        lockPanel.add(lockLabel);
        lockPanel.add(lockButton);
        lockPanel.add(unlockButton);
        frame.add(lockPanel);

        
        JPanel modePanel = new JPanel();
        JButton sleepModeButton = new JButton("Sleep Mode");
        JButton vacationModeButton = new JButton("Vacation Mode");
        sleepModeButton.addActionListener(e -> applyMode("Sleep"));
        vacationModeButton.addActionListener(e -> applyMode("Vacation"));
        modePanel.add(new JLabel("Select Mode"));
        modePanel.add(sleepModeButton);
        modePanel.add(vacationModeButton);
        frame.add(modePanel);

        frame.setVisible(true);
    }

    private void updateLight(boolean state) {
        smartHome.setDeviceState(light, state);
        lightLabel.setText("Light is now " + (state ? "ON" : "OFF"));
        JOptionPane.showMessageDialog(null, "Light is now " + (state ? "ON" : "OFF"), "Smart Home System", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateThermostat() {
        try {
            int temp = Integer.parseInt(thermostatInput.getText());
            smartHome.setDeviceState(thermostat, temp);
            thermostatLabel.setText("Thermostat: " + temp + "°C");
            JOptionPane.showMessageDialog(null, "Thermostat set to " + temp + "°C", "Smart Home System", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid temperature input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateLock(boolean state) {
        smartHome.setDeviceState(lock, state);
        lockLabel.setText("Lock: " + (state ? "LOCKED" : "UNLOCKED"));
        JOptionPane.showMessageDialog(null, "Lock is now " + (state ? "LOCKED" : "UNLOCKED"), "Smart Home System", JOptionPane.INFORMATION_MESSAGE);
    }

    private void applyMode(String mode) {
        smartHome.sendMessage(mode);
        JOptionPane.showMessageDialog(null, mode + " mode applied", "Smart Home System", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new GUI();
    }
}