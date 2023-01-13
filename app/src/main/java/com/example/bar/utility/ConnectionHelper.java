package com.example.bar.utility;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    String uname, pass, ip, port, database;
    @SuppressLint("NewApi")
    public Connection conclass() {
        ip = "192.168.1.107";
        database = "bar";
        uname = "postgre";
        pass = "Durr_poland2";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+uname+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);

        } catch (Exception e) {
            Log.e("Error ", e.getMessage());
        }
        return connection;
    }

}
