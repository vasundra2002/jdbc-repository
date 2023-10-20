package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBCConnection {

    public static void main(String[] args) {
        Connection connection=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_first_db", "vasundra", "vasundra");
            if(connection!=null)
            {
                System.out.println("Conneted!!");
            }
            else
            {
                System.out.println("Problem in database connection!!");
            }
            
            Statement statement=connection.createStatement();
            String sql="select * from customer";
            
            ResultSet result=statement.executeQuery(sql); 
            
            
            while(result.next())
            {
                System.out.println(result.getInt(1)+ " "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4)+" "+result.getLong(5));
            }
        } 
        
        catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
        catch (SQLException e) {
            
            e.printStackTrace();
        }
        
       
        
        
    }

}