/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oladb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heleno
 */
public class TesteDB {
    public static void main(String[] args){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/alomundodb", "usuario", "123");
            PreparedStatement sql = conexao.prepareStatement("insert into contato values (?,?)");
            sql.setString(1, "Heleno");
            sql.setInt(2, 30);
            sql.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TesteDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TesteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
