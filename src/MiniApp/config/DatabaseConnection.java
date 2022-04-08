/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniApp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Cesar Eduardo Ortiz Salazar
 */
public class DatabaseConnection {
    
    public DatabaseConnection(){}
    
    private Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_test", "root", "");
        }catch(SQLException ex){
            this.exception(ex);
        }
        return conn;
    }
    
    public ResultSet excecuteQuery(String query){
        Statement stmt;
        ResultSet rs = null;
        try{
            stmt = this.getConnection().createStatement(); 
            rs = stmt.executeQuery(query);
        }catch(SQLException ex){
            this.exception(ex);
        }
        return rs;
    }
    
    public void executeUpdate(String query){
        Statement stmt;
        try{
            stmt = this.getConnection().createStatement(); 
            stmt.executeUpdate(query);
        }catch(SQLException ex){
            this.exception(ex);
        }
    }
    
    public void exception(SQLException ex){
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
}
