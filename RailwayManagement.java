package com.jspiders.tasks;
import java.util.Scanner;
public class RailwayManagement {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		TrainDAO trainDAO = new TrainDAO();
		PassengerDAO passengerDAO = new PassengerDAO();
		ReservationDAO reservationDAO = new ReservationDAO();
		 while(true) {
			 System.out.println("----------Railway Reservation System-----------");
			 System.out.println("1.Display Trains");
			 System.out.println("2.Book Ticket");
			 System.out.println("3.Cancel Ticket");
			 System.out.println("4.Check Reservation");
			 System.out.println("5.Exit");
			 System.out.println("Enter Choice : ");
			 int choice = sc.nextInt();
			 switch(choice) {
			 case 1:
				 trainDAO.displayTrains();
				 break;
			 case 2:
				 trainDAO.displayTrains();
				 System.out.print("Enter Train ID: ");
				 int trainId = sc.nextInt();
				 sc.nextLine();
				 System.out.println("Enter Name: ");
				 String name = sc.nextLine();
				 System.out.print("Enter Age: ");
				 int age = sc.nextInt();
				 sc.nextLine();
				 System.out.print("Enter Gender: ");
				 String gender  = sc.nextLine();
				 Passenger p = new Passenger(name, age, gender);
				 int passengerID =  passengerDAO.addPassenger(p);
				 reservationDAO.bookTicket(passengerID, trainId);
				 break;
			 case 3:
				 System.out.println("Enter PNR Number : ");
				 String cancel = sc.next();
				 reservationDAO.cancelTicket(cancel);
				 break;
			 case 4 : 
				 System.out.println("Enter PNR Number : ");
				 String pnr = sc.next();
				 reservationDAO.displayReservation(pnr);
				 break;
			 case 5 :
				 System.out.println("-Thank You-");
				 System.exit(0);
			 default:
				 System.out.println("Invalid Choice!!");
				 break;
			 }
		 }
			
		
		
	}

}
