
package com.medhat.banking;

import static com.medhat.banking.DBConnection.connect;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Profile {
    Profile(JFrame parent , int userid){
        
        String uNameV = "" , genderV = "" , bDayV = "" ,phoneV = "" ,emailV = "";
        try{
            String sql = "select Uname , Gender , Day , Month , Year , Phone , Email from user where id = ?";
            PreparedStatement query = connect().prepareStatement(sql);
            query.setInt(1, userid);
            ResultSet result = query.executeQuery();
            
            if(result.next())
            {
                uNameV = result.getString(1);
                genderV = result.getString(2);
                bDayV = result.getString(3) + " / " + result.getString(4) + " / " + result.getString(5);
                phoneV = result.getString(6);
                emailV = result.getString(7);
            }
        }
        
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        
        JDialog window = new JDialog(parent , "Profile" , true);
        window.setSize(300, 300);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(6,2, 0 , 10));
        
        JLabel uName = new JLabel("Username");
        JLabel uNameValue = new JLabel(uNameV);
        
        JLabel gender = new JLabel("Gender");
        JLabel genderValue = new JLabel(genderV);
        
        JLabel bDay = new JLabel("BirthDate");
        JLabel bDayValue = new JLabel(bDayV);
        
        JLabel phone = new JLabel("Phone");
        JLabel phoneValue = new JLabel(phoneV);
        
        JLabel email = new JLabel("Email");
        JLabel emailValue = new JLabel(emailV);
        
        
        panel.add(uName);
        panel.add(uNameValue);
        panel.add(gender);
        panel.add(genderValue);
        panel.add(bDay);
        panel.add(bDayValue);
        panel.add(phone);
        panel.add(phoneValue);
        panel.add(email);
        panel.add(emailValue);
        
        
        window.add(panel);
        
        window.setVisible(true);

    
    }
    
    
}
