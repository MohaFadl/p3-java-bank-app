package com.medhat.banking;

import static com.medhat.banking.DBConnection.connect;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WithDepo {
    double balanceV = 0;
    
    WithDepo(int userid , int type){
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
        
        JFrame window = new JFrame("");
        window.setSize(300, 300);
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        
        
         if(type == 1){
            window.setTitle("Deposite");
        }
        else{
           window.setTitle("Withdraw");
        }
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(5, 1, 0, 10));
        
        JLabel balance = new JLabel("Balance", SwingConstants.CENTER);    
        JLabel bV= new JLabel("" + balanceV , SwingConstants.CENTER);
        JTextField input = new JTextField();
        
        JButton submit = new JButton("Submit");
        submit.setBackground(new Color(52, 73, 94));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.addActionListener((e) -> {
            String sqlOne = "update user set balance = ? where id = ?";
            String sqlTwo = "insert into transaction (uid,amount,day,month,year,type) values (? , ? , ? , ? ,?,?)";
            double amount = Double.parseDouble(input.getText());
            if(type == 0){
                if(amount > balanceV){
                System.out.println("Not enough money");
            }
            else {
            balanceV = balanceV - amount;
                try {
                    PreparedStatement stmtOne = connect().prepareStatement(sqlOne);
                    stmtOne.setDouble(1, balanceV);
                    stmtOne.setInt(2, userid);
                    stmtOne.executeUpdate();
                    
                    PreparedStatement stmtTwo = connect().prepareStatement(sqlTwo);
                    stmtTwo.setInt(1, userid);
                    stmtTwo.setDouble(2, amount);
                    stmtTwo.setInt(3, LocalDate.now().getDayOfMonth());
                    stmtTwo.setInt(4, LocalDate.now().getMonthValue());
                    stmtTwo.setInt(5, LocalDate.now().getYear());
                    stmtTwo.setInt(6, type);
                    stmtTwo.executeUpdate();
                    
                    window.dispose();
                    Home h = new Home(userid);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            }
            else if (type == 1){
            balanceV = balanceV + amount;
                try {
                    PreparedStatement stmtOne = connect().prepareStatement(sqlOne);
                    stmtOne.setDouble(1, balanceV);
                    stmtOne.setInt(2, userid);
                    stmtOne.executeUpdate();
                    
                    
                    PreparedStatement stmtTwo = connect().prepareStatement(sqlTwo);
                    stmtTwo.setInt(1, userid);
                    stmtTwo.setDouble(2, amount);
                    stmtTwo.setInt(3, LocalDate.now().getDayOfMonth());
                    stmtTwo.setInt(4, LocalDate.now().getMonthValue());
                    stmtTwo.setInt(5, LocalDate.now().getYear());
                    stmtTwo.setInt(6, type);
                    stmtTwo.executeUpdate();
                    
                    
                    
                    window.dispose();
                    Home h = new Home(userid);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
   
        
        
        JButton back = new JButton("Back");
        back.setBackground(new Color(52, 73, 94));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.addActionListener((e) -> {
            window.dispose();
            Home h = new Home(userid);
        });
        
        panel.add(balance);
        panel.add(bV);
        panel.add(input);
        panel.add(submit);
        panel.add(back);
        
        window.add(panel);
        
        window.setVisible(true);
    }
}
