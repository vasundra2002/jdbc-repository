package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CallingFunction {

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
            
        
            
            CallableStatement callableStatement=connection.prepareCall("{?=call order_priority(?)}");
            System.out.println("Enter the order amount:");
            double amount=Double.parseDouble(bufferedReader.readLine());
            callableStatement.setDouble(2, amount);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            
        
            callableStatement.execute();
            
            System.out.println(callableStatement.getString(1));
            
            
        
            
        }
        catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
        catch (SQLException e) {
            
            e.printStackTrace();
        }

    }

}
