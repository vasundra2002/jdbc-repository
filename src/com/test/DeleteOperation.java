package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteOperation {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Connection connection=null;
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
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
            
            PreparedStatement preparedStatement=connection.prepareStatement("delete from customer where cid=?");
        
            System.out.println("Enter existing user id:");
            int cid=Integer.parseInt(bufferedReader.readLine());
            preparedStatement.setInt(1, cid);
            
            
            if(preparedStatement.executeUpdate()>0)
            {
                System.out.println("Record deleted!!");
                
                
            }
            else
            {
                System.out.println("Problem in update operation!!");
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
