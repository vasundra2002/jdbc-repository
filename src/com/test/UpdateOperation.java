package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateOperation {

    public static void main(String[] args) throws IOException {
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
            
            PreparedStatement preparedStatement=connection.prepareStatement("update customer set cname=?, caddress=?,email=?,phone=? where cid=?");
            System.out.println("Enter new name:");
            preparedStatement.setString(1, bufferedReader.readLine());
            System.out.println("Enter new city name:");
            preparedStatement.setString(2, bufferedReader.readLine());
            System.out.println("Enter new email id:");
            preparedStatement.setString(3, bufferedReader.readLine());
            System.out.println("Enter new phone number:");
            preparedStatement.setLong(4, Long.parseLong(bufferedReader.readLine()));
            System.out.println("Enter existing user id:");
            int cid=Integer.parseInt(bufferedReader.readLine());
            preparedStatement.setInt(5, cid);
            
            
            if(preparedStatement.executeUpdate()>0)
            {
                System.out.println("Record updated!!");
                
                preparedStatement=connection.prepareStatement("select * from customer where cid=?");
                preparedStatement.setInt(1, cid);
                ResultSet resultSet=preparedStatement.executeQuery();
                
                while(resultSet.next())
                {
                    System.out.println(resultSet.getInt(1)+ " "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getLong(5));

                }
                
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
