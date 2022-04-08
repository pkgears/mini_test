/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniApp.models;

import static MiniApp.models.Model.conn;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class Payment extends Model {
    private int id;
    private float amount;
    private Date date;
    private int user_id;
    public User user;
    
    public Payment(){}
    
    public Payment(float amount, String date, int user_id){
        this.amount = amount;
        this.date = Date.valueOf(date);
        this.user_id = user_id;
        try {
            this.user = User.find(this.user_id);
        }catch(SQLException ex){
            conn.exception(ex);
        }
    }
    
    public Payment(int id, float amount, String date, int user_id){
        this.id = id;
        this.amount = amount;
        this.date = Date.valueOf(date);
        this.user_id = user_id;
        try {
            this.user = User.find(this.user_id);
        }catch(SQLException ex){
            conn.exception(ex);
        }
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setAmount(float amount){
        this.amount = amount;
    }
    
    public float getAmount(){
        return this.amount;
    }
    
    public void setDate(String date) {
        this.date = Date.valueOf(date);
    } 
    
    public String getDate(){ 
       return this.date.toString();
    }
    
    public void setUserId(int user_id){
        this.user_id = user_id;
        try {
            this.user = User.find(this.user_id);
        } catch(SQLException ex){
            conn.exception(ex);
        }
    }
    
    
    /* CLASS METHODS */
    
    public static ArrayList all(int user_id){
        ArrayList<Payment> payments = new ArrayList<>();
        try{
            ResultSet rs = User.conn.excecuteQuery("SELECT * FROM payments WHERE user_id = "+user_id+";");
            
            while(rs.next()){
                Payment next_payment = new Payment(rs.getInt(1), rs.getFloat("amount"), rs.getString("date"), rs.getInt("user_id"));
                payments.add(next_payment);
            }
            
        }catch(SQLException ex){
            conn.exception(ex);
        }
        return payments;
    }
    
    public static Payment find(int id) throws SQLException{
        Payment payment = null;
        try{
            ResultSet rs = Payment.conn.excecuteQuery("SELECT * from users WHERE id = " + id + ";");          
            if(rs.next()){
                payment = new Payment(rs.getInt(1), rs.getFloat("amount"), rs.getString("date"), rs.getInt("user_id"));
            }else{
                throw new SQLException("Payment with ID "+ id + " was not found");
            }
        }catch(SQLException ex){
            conn.exception(ex);
        }
        return payment;
    }
    
    public static Payment create(float amount, String date, int user_id) throws SQLException {
        Payment new_payment = new Payment(amount, date, user_id);
        String insert_query = "INSERT INTO payments (amount, date, user_id) VALUES ('"+amount+"','"+date+"','"+user_id+"');";
        User.conn.executeUpdate(insert_query);
        return new_payment;
    }
}
