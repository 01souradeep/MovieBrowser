/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ramaih.project;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author souradeep
 */
public class MovieConn {
    Connection conn = null;
 public static Connection connectdb(){
     try{
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/libman?useUnicode=true&amp;amp;characterEncoding=utf-8&amp;amp;autoReconnect=true","root","iam100%good");
         //JOptionPane.showMessageDialog(null,"Connection Established");
         return conn;
     }  catch (Exception e) {
          JOptionPane.showMessageDialog(null, e);
        }
        return null;
 }    
    
}
