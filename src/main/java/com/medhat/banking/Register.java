package com.medhat.banking;

import static com.medhat.banking.DBConnection.connect;
import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class Register {
    Register () {
        JFrame window = new JFrame("Banking | Register");
        window.setSize(400, 400);
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Username
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Gender
        JLabel genderLabel = new JLabel("Gender");
        JComboBox genderBox = new JComboBox();
        genderBox.addItem("Male");
        genderBox.addItem("Female");
        genderBox.setBackground(Color.WHITE);
        panel.add(genderLabel);
        panel.add(genderBox);

        // Email
        JLabel emailLabel = new JLabel("Email");
        JTextField emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone Number");
        JTextField phoneField = new JTextField();
        panel.add(phoneLabel);
        panel.add(phoneField);

        // Building Number
        JLabel buildingLabel = new JLabel("Building Number");
        JTextField buildingField = new JTextField();
        panel.add(buildingLabel);
        panel.add(buildingField);

        // Street
        JLabel streetLabel = new JLabel("Street");
        JTextField streetField = new JTextField();
        panel.add(streetLabel);
        panel.add(streetField);

        // City
        JLabel cityLabel = new JLabel("City");
        JTextField cityField = new JTextField();
        panel.add(cityLabel);
        panel.add(cityField);

        // Day
        JLabel dayLabel = new JLabel("Day");
        JComboBox dayCombo = new JComboBox();
        dayCombo.setBackground(Color.white);
        for (int i = 1; i <= 31; i++) {
            dayCombo.addItem(i);
        }
        panel.add(dayLabel);
        panel.add(dayCombo);

        // Month
        JLabel monthLabel = new JLabel("Month");
        JComboBox monthCombo = new JComboBox();
        monthCombo.setBackground(Color.white);
        for (int i = 1; i <= 12; i++) {
            monthCombo.addItem(i);
        }
        panel.add(monthLabel);
        panel.add(monthCombo);

        // Year
        JLabel yearLabel = new JLabel("Year");
        JComboBox yearCombo = new JComboBox();
        yearCombo.setBackground(Color.white);
        for (int i = 1800; i <= 2025; i++) {
            yearCombo.addItem(i);
        }
        panel.add(yearLabel);
        panel.add(yearCombo);

        // Buttons
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(52, 73, 94));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> {
            window.dispose();
            Login l = new Login();
        });

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(52, 73, 94));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(e -> {
            try {
                String sql = "insert into user (Uname, Pass, Gender, Day, Month, Year, Email, Phone, BN, Street, City) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement query = connect().prepareStatement(sql);
                query.setString(1, usernameField.getText());
                query.setString(2, new String(passwordField.getPassword()));
                query.setString(3, (String) genderBox.getSelectedItem());
                query.setInt(4, (int) dayCombo.getSelectedItem());
                query.setInt(5, (int) monthCombo.getSelectedItem());
                query.setInt(6, (int) yearCombo.getSelectedItem());
                query.setString(7, emailField.getText());
                query.setString(8, phoneField.getText());
                query.setString(9, buildingField.getText());
                query.setString(10, streetField.getText());
                query.setString(11, cityField.getText());

                query.executeUpdate();
                JOptionPane.showMessageDialog(window, "Registration successful!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(window, "Error: " + ex.getMessage());
            }
        });

        panel.add(loginButton);
        panel.add(registerButton);

        window.add(panel);
        window.setVisible(true);
    }
}
