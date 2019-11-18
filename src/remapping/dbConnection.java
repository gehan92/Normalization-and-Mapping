/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Gehan Ishanka
 */
public class dbConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        CreatTable();
    }
    
    public static void CreatTable() throws Exception {
       try{
        
        Connection con = getConnection();
        PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Table(id int NOT NULL AUTO_INCREMENT,first varchar(255),last varchar(255),PRIMARY KEY(id))");
        create.executeUpdate();}catch (Exception e)
                {
                    System.out.println(e);
                
                }
       finally
       {
           System.out.println("Function compieete");
       }
    }
    
    
    
    
    public static Connection getConnection() throws Exception
    {
        try{
        String driver = "com.mysql.jdbc.Driver";
        String url ="jdbc:mysql://localhost:3306/dbms";
        String username ="root";
        String password ="";
        Class.forName(driver);
        
        Connection conn = DriverManager.getConnection (url,username,password);
        System.out.println("Connected");
        return conn;
        
        }catch(Exception e)
                {
                    System.out.println(e);
                
                }
        return null;
        
    
    }
    
    
}
