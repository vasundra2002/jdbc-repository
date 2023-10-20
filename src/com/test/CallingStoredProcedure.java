package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallingStoredProcedure {

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
            
            System.out.println("Enter customerId to get the details:");
            int cid=Integer.parseInt(bufferedReader.readLine());
            
            CallableStatement callableStatement=connection.prepareCall("{call fetch_customer_details(?)}");
            callableStatement.setInt(1, cid);
            
            
            ResultSet result=callableStatement.executeQuery();
            
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
