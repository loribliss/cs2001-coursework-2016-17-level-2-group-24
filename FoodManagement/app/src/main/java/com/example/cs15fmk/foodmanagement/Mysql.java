package com.example.cs15fmk.foodmanagement;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static android.provider.Telephony.Carriers.PASSWORD;
import static java.util.jar.Pack200.Packer.PASS;

/**
 * Created by Frankie on 12/02/2017.
 */
public class Mysql extends AppCompatActivity{
    private static final String url = "jdbc:mysql://192.168.0.23:3306/grp24_student";
    private static final String user = "Frankie";
    private static final String password = "password";
    private static final String TAG = "MyActivity";
    String apple[] ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        main(apple);
    }
    public static void main(String args[]) {
        System.out.println("Hello");
        testDB();
    }
      /*  try {
            Statement stmt;
            ResultSet rs;

            //Register the JDBC driver for MySQL
            Class.forName("com.mysql.jdbc.Driver");

            Log.d(TAG, "Connecting");
            Connection con = DriverManager.getConnection(url, user, password);

            //Statement select = con.createStatement();

            *//*rs = select.executeQuery("SELECT ID, name, height FROM student");

            System.out.println("Some results:");
            while (rs.next()) { // process results one row at a time
                int key = rs.getInt(1);
                int field = rs.getInt(2);
                int amt = rs.getInt(3);

                System.out.println("key = " + key);
                System.out.println("Amt = " + amt);
                System.out.println("Field = " + field);*//*

                //Get a Statement object
            stmt = con.createStatement();


            // Insert a row
            stmt.executeUpdate("INSERT INTO account" +
                    "VALUES ('Bob', 'bob@gmail.com', 'bob')");
            Log.d(TAG, "INSERTING");
            }

            catch(Exception e){
                System.out.println(e);
                Log.d(TAG, "ERROR "+ e);
            }*/




   /* public void apple(){
        try {
            Statement stmt;
            ResultSet rs;

            //Register the JDBC driver for MySQL

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://IP:PORT/DBNAME";

            Connection con = DriverManager.getConnection( url,"USER","PASSWORD");

            //Get a Statement object
            stmt = con.createStatement();


            // Insert a row
            stmt.executeUpdate( "INSERT INTO account " +
                    "VALUES ('Bob', 'bob@gmail.com', 'bob')");
        }
        catch (Exception e) {
           Log.d(TAG, "dsadas" + e);
        }
    }*/



    public static void testDB(){
        Connection conn = null;
        Statement stmt = null;
        try{
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");

           Log.d(TAG,"Connecting to a selected database...");
            conn = DriverManager.getConnection(url, user, password);
            Log.d(TAG, "Connected database successfully...");

            Log.d(TAG,"Inserting records into the table...");
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO account" +
                    "VALUES ('Bob', 'bob@gmail.com', 'bob')");

            Log.d(TAG,"Inserted records into the table...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");


    }
}
