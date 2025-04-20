package com.medhat.banking;

import static com.medhat.banking.DBConnection.connect;
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class Home {
    double balanceV = 0;
    
    Home(int userid){
        
        try{
            String sql = "select balance from user where id = ?";
            PreparedStatement query = connect().prepareStatement(sql);
            query.setInt(1, userid);
            ResultSet result = query.executeQuery();
            
            if(result.next())
            {
             balanceV = result.getDouble(1);
            }
        }
        
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        
        
        
        JFrame window = new JFrame("Home");
        window.setSize(300, 300);
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());
        
        //menuBar
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem profile = new JMenuItem("Profile");
        profile.addActionListener((e) -> {
            Profile p = new Profile(window , userid);
        });
        
        menu.add(profile);
        menuBar.add(menu);
        window.setJMenuBar(menuBar);
        
        //centralPanel
        JPanel centralPanel = new JPanel();
        centralPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centralPanel.setLayout(new GridLayout(1,2,0,10));
        
        JLabel balance = new JLabel("Balance" , SwingConstants.CENTER);
        balance.setFont(new Font("Arial", 1, 20));
        JLabel balanceValue = new JLabel( balanceV + "", SwingConstants.CENTER);
        balanceValue.setFont(new Font("Arial", 0, 20));
        
        centralPanel.add(balance);
        centralPanel.add(balanceValue);
        
        window.add(centralPanel , BorderLayout.CENTER);
        
        //bottompanel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centralPanel.setLayout(new GridLayout(1,2,20,0));
        
        JButton deposite = new JButton("Deposite");
        deposite.setBackground(new Color(52, 73, 94));
        deposite.setForeground(Color.WHITE);
        deposite.setFocusPainted(false);
        deposite.addActionListener((e) -> {
            window.dispose();
            WithDepo wd = new WithDepo(userid , 1);
        });
        
        
        JButton withdraw = new JButton("Withdraw");
        withdraw.setBackground(new Color(52, 73, 94));
        withdraw.setForeground(Color.WHITE);
        withdraw.setFocusPainted(false);
        withdraw.addActionListener((e) -> {
            window.dispose();
            WithDepo wd = new WithDepo(userid , 0);
        });
        
        bottomPanel.add(withdraw);
        bottomPanel.add(deposite);
        
        
        window.add(bottomPanel , BorderLayout.SOUTH);
        
        
        window.setVisible(true);
        
    
        
    }
}
