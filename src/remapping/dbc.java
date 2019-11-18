/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Gehan Ishanka
 */
public class dbc {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public dbc()
    {
        try{
            
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms","root", "");
                    
            st = con.createStatement();
            
        }catch(Exception ex){
        System.out.println("Erro"+ex);
        }
    
    }
    
    public void getData()
    {
        try{
        
        

            String query = "CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL AUTO_INCREMENT,first varchar(255),last varchar(255),PRIMARY KEY(id))";
             System.out.println("++++++++++++++++++++++++===============>Records from Database");
            rs = st.executeQuery(query);
            System.out.println("++++++++++++++++++++++++===============>Records from Database");
            while(rs.next())
            {
                String TableName = rs.getString("name");
                String Attribute = rs.getString("attribute");
                System.out.println("Name "+TableName+ "Attribute "+Attribute);
            
            }
            
        
        }catch(Exception ex)
        {
        }
    
    }
    
}
