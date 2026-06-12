package com.jspiders.tasks;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class PassengerDAO {
	public int addPassenger(Passenger p) {
		int passengerId = 0;
		try {
			Connection con = DBConnection.getConnection();
			String query = "INSERT INTO PASSENGER(PASSENGER_NAME,AGE,GENDER) VALUES(?,?,?)";
			PreparedStatement psmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1,  p.getPassengerName());
			psmt.setInt(2,  p.getAge());
			psmt.setString(3,  p.getGender());
			int count  = psmt.executeUpdate();
			if(count!= 0) {
				System.out.println("Passenger Added");
				
			}
			else {
				System.out.println("Try again - addd passenger");
			}
			ResultSet rs = psmt.getGeneratedKeys();
			if(rs.next()) {
				passengerId =  rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();

		}
		return passengerId;
	}

}
