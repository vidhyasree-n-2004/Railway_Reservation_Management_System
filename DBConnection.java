package com.jspiders.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String dburl ="jdbc:mysql://localhost:3306/railwaydb";
    private static final String user="root";
    private static final String password="Vidhya@2004";
    
    public static Connection getConnection() {
    	Connection con = null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(dburl,user,password);
    	
    	}catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return con;
    	
    	}
    }
