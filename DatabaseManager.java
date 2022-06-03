package com.example.programming_3_project;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    static final String DatabaseLink = "jdbc:mysql://localhost:3306/weather";
    private String username;
    private String password;
    private static Connection conn;
    private static Statement st;
    private static String query = "SELECT cityid, cityname, country, temperature, overallweather, humidity FROM locations ";
    static int ID = 34;

    private static boolean successfulConnect = false;

    DatabaseManager(String username, String password){
        this.username = username;
        this.password = password;
    }

    void connectToDB(){


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException f){
            System.out.println(":(");
        }

        try{

            conn = DriverManager.getConnection(DatabaseLink, username, password);
        }

        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Could not connect to database. Password and/or username is wrong");
            successfulConnect = false;
            return;
        }


        System.out.println("Successfuly connected to DB");
        successfulConnect = true;

    }

    boolean getConnectionStatus(){
        return successfulConnect;
    }

    String query(String arg){
        try{
            String condition = "WHERE cityname = " + "'" + arg + "'";
            System.out.println(query + condition);
            st = conn.createStatement();
            ResultSet r = st.executeQuery(query + condition);
            r.next();
            System.out.println(r);

            String result = "City ID:" + r.getInt("cityid") + "\n" + "City name" + r.getString("cityname") + "\n" + "Country:" + r.getString("country") + "\n" + "Temperature:" + r.getInt("temperature") + "\n" + "Overall Feel:" + r.getString("overallweather") + "\n" + "Humidity:" + r.getInt("humidity");

            return result;
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return "Not found";
    }

    void closeDB() throws SQLException{
        conn.close();
    }

    String addElement(ArrayList<String> args){
        try{
            st = conn.createStatement();
            String insert = "INSERT INTO locations VALUES(" + String.valueOf(ID) + ", ";

            for(int i = 0; i <args.size(); i++){
                if(i != args.size() - 1)
                    insert += "'" + args.get(i) + "', ";
                else
                    insert += "'" + args.get(i) + "');";
            }
            ID++;
            st.executeUpdate(insert);
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Failed to insert element";
        }

        return "Element inserted successfuly";
    }

    void setCredentials(String user, String password){
        this.username = user;
        this.password = password;
    }




}
