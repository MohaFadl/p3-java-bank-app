package com.medhat.banking;

import static com.medhat.banking.DBConnection.connect;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Login {

     Login() {
        JFrame window = new JFrame("Banking | Login");
        window.setSize(350, 200);
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon bg = new ImageIcon("C:\\Users\\Mohamed Medhat\\Downloads\\image-removebg-preview.png");
        g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
};
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(240, 240, 240));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField username = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField password = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(52, 73, 94));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener((e) -> {
           try{
           String sql = "Select ID from user where Uname = ? and Pass = ?";
           PreparedStatement query = connect().prepareStatement(sql);
           query.setString(1,username.getText());
           query.setString(2,new String(password.getPassword()));
           
           ResultSet result = query.executeQuery();
           
           if(result.next()){
               int userID = result.getInt(1);
               window.dispose();
               Home h = new Home(userID);
           }
           
           else {
               System.out.println("Wrong username / password");
           }
               
               
           
           } 
           
           catch (SQLException ex){
           System.out.println(ex.getMessage());
           
           }
            
            
        });
        

        JButton regButton = new JButton("Register");
        regButton.setBackground(new Color(52, 73, 94));
        regButton.setForeground(Color.WHITE);
        regButton.setFocusPainted(false);
        regButton.addActionListener((e) -> {
            window.dispose();
            Register r = new Register();
        });

        // Add components to panel
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(regButton);
        panel.add(loginButton);

        window.add(panel);
        window.setVisible(true);
    }
}
