package com.jspiders.tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class ReservationDAO {
	public void bookTicket(int passengerId,int trainId) {
		Connection con = null;
		try {
			con=DBConnection.getConnection();
			String query = "SELECT AVAILABLE_SEATS FROM TRAIN WHERE TRAIN_ID=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, trainId);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				int seats = rs.getInt("AVAILABLE_SEATS");
				if(seats>0) {
					String pnr = generatePNR();
					String insertReservation = "INSERT INTO RESERVATION(PASSENGER_ID,TRAIN_ID,PNR_NUMBER,STATUS) VALUES(?,?,?,?)";
					PreparedStatement psmt1 = con.prepareStatement(insertReservation);
					psmt1.setInt(1, passengerId);
					psmt1.setInt(2, trainId);
					psmt1.setString(3, pnr);;
					psmt1.setString(4,"BOOKED");
					psmt1.executeUpdate(); //EXECUTES QUERY
					String updateSeat ="UPDATE TRAIN SET AVAILABLE_SEATS = AVAILABLE_SEATS-1 WHERE TRAIN_ID = ?";
					PreparedStatement psmt2=con.prepareStatement(updateSeat);
					psmt2.setInt(1,trainId);
					psmt2.executeUpdate();
					con.setAutoCommit(false);
					System.out.println("Ticket Booked Successfully");
					System.out.println("PNR Number: "+pnr);
					
				}
				else {
					System.out.println("No seats available");
				}
				
			}else {
				System.out.println("Invalid train id - Try again");
			}
		}catch(Exception e) {
				e.printStackTrace();
			 }

		    }
    private String generatePNR() {
    	Random random = new Random();
    	int number = 100000 + random.nextInt(900000);
    	return "PNR"+number;
    	}
    public void cancelTicket(String pnr) {
    	Connection con= null;
    	try {
    		con = DBConnection.getConnection();
    		con.setAutoCommit(false);
    		String query = "SELECT TRAIN_ID FROM RESERVATION WHERE PNR_NUMBER=?";
    		PreparedStatement psmt1=con.prepareStatement(query);
    		psmt1.setString(1, pnr);
    		ResultSet rs= psmt1.executeQuery();
    		if(rs.next()) {
    			int trainId =rs.getInt("TRAIN_ID");
    			String cancelQuery = "UPDATE RESERVATION SET STATUS='CANCELLED' WHERE PNR_NUMBER = ?";
    			PreparedStatement psmt2 = con.prepareStatement(cancelQuery);
    			psmt1.setInt(1,trainId);
    			con.commit();
    			System.out.println("Ticket Cancelled Successfully");
    		} else {
    			System.out.println("Invalid PNR number");
    		  }
    		}
    		 catch(Exception e) {
    			 try {
    				 con.rollback();
    			 } catch (Exception e1) {
    				 e1.printStackTrace();
    			 }
    				e.printStackTrace();
    			 }

    		    }
       public void displayReservation(String pnr) {
    	   try {
    		   Connection con = DBConnection.getConnection();
    		   String query = "SELECT p.PASSENGER_NAME, t.TRAIN_NAME, r.PNR_NUMBER, r.STATUS "
    		             + "FROM RESERVATION r "
    		             + "JOIN PASSENGER p ON r.PASSENGER_ID = p.PASSENGER_ID "
    		             + "JOIN TRAIN t ON r.TRAIN_ID = t.TRAIN_ID "
    		             + "WHERE r.PNR_NUMBER = ?";
    		   PreparedStatement psmt = con.prepareStatement(query);
				psmt.setString(1, pnr);
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {
					System.out.println("Passenger Name : "+rs.getString("PASSENGER_NAME"));
					System.out.println("Train Name : "+rs.getString("Train_NAME"));
					System.out.println(" PNR NUMBER : "+rs.getString("PNR_NUMBER"));
					System.out.println("Status : "+rs.getString("STATUS"));
				} else {
					System.out.println("Reservation not found|Exists!!");
				}
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   }
       }
    }
