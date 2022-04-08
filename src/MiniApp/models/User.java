/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniApp.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Cesar Eduardo Ortiz Salazar
 */
public class User extends Model {
    private int id;
    private String name;
    private Date birthdate;
    
    /* Constructores */
    public User(){}
    
    public User(String name, Date birthdate){
        this.name = name;
        this.birthdate = birthdate;
    }
    
    public User(int id, String name, Date birthdate){
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }
    
    /* CLASS METHODS */
    
    public static ArrayList all(){
        ArrayList<User> users = new ArrayList<>();
        try{
            ResultSet rs = User.conn.excecuteQuery("SELECT * FROM users;");
            
            while(rs.next()){
                User next_user = new User(rs.getInt(1), rs.getString("name"), rs.getDate("birthdate"));
                users.add(next_user);
            }
            
        }catch(SQLException ex){
            conn.exception(ex);
        }
        return users;
    }
    
    public static User find(int id) throws SQLException{
        User user = null;
        try{
            ResultSet rs = User.conn.excecuteQuery("SELECT * from users WHERE id = " + id + ";");          
            if(rs.next()){
                user = new User(rs.getInt(1), rs.getString("name"), rs.getDate("birthdate"));
            }else{
                throw new SQLException("User with ID "+ id + " was not found");
            }
        }catch(SQLException ex){
            conn.exception(ex);
        }
        return user;
    }
    
    public static User create(String name, String birthdate) throws SQLException {
        User new_user = new User();
        new_user.setName(name);
        new_user.setBirthdate(birthdate);
        String insert_query = "INSERT INTO users (name, birthdate) VALUES ('"+name+"','"+birthdate+"');";
        User.conn.executeUpdate(insert_query);
        return new_user;
    }
    
    /* INSTANCE METHODS */
    
    /* Update */
    public User update(String name, String birthdate) throws SQLException{
        this.setName(name);
        this.setBirthdate(birthdate);
        
        this.save();
        
        return this;
    }
        
    /* Save */
    public User save() throws SQLException {
        String insert_query = "UPDATE users SET name = '"+this.name+"', birthdate = '"+this.birthdate+"' WHERE id = "+this.id+";";
        User.conn.executeUpdate(insert_query);
        
        return this;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = Date.valueOf(birthdate);
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getBirthdate(){ 
       return this.birthdate.toString();
    }
    
    public ArrayList payments(){
        return Payment.all(id);
    }
    
    /* Other methods */
    
    @Override
    public String toString(){
        return "ID: " + this.id + " NAME: " + this.name + " BIRTHDATE: " + this.birthdate;
    }
}
