package com.jspiders.tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

public class TrainDAO {
	public void displayTrains() {
		Connection con=DBConnection.getConnection();
		String query = "SELECT * FROM TRAIN";
		try {
			PreparedStatement psmt = con.prepareStatement(query);
			ResultSet rs= psmt.executeQuery();
			ResultSetMetaData rmd = rs.getMetaData();
			int count = rmd.getColumnCount();
			System.out.println("+----------------------------------------------------------------------------+");
			for(int i=1;i<=count;i++) {
				System.out.printf("%-15s",rmd.getColumnName(i).toUpperCase());
			
			}
			System.out.println();
			System.out.println("+----------------------------------------------------------------------------+");
			while(rs.next()) {
				for(int i=1;i<=count;i++) {
					System.out.printf("%-15s",rs.getObject(i));
				}
			}
			System.out.println("+----------------------------------------------------------------------------+");
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}