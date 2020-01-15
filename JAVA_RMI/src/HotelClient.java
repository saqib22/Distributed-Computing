/*
* Author : Saqib Ali Khan
* BSCS6C
* SEECS-NUST
* */

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelClient {
    public static HotelReservation stub;

    private HotelClient(){}

    private void connectServer() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
        stub = (HotelReservation) registry.lookup("HotelReservation");
        System.out.println("Connected to Server!");
    }

    public static void list_rooms() throws RemoteException {
        int rooms_available[] = stub.list();
        int prices[] = stub.getPrices();
        for (int i = 0; i < rooms_available.length; i++){
            System.out.println(rooms_available[i] +
                    " rooms of type " + i +" are available for " + prices[i] + " Euros per night");
        }
    }

    public static void print_guests() throws RemoteException {
        ArrayList<Guest> guests = stub.guests();
        if (guests.size() == 0){
            System.out.println("There are no guests reserved!");
        }
        for (Guest guest: guests){
            System.out.println(guest.name);
        }
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        HotelClient client = new HotelClient();
        client.connectServer();

        if (args.length == 0){
            System.out.println("\nWelcome to Hotel Reservation System");
            System.out.println("*****Please provide the following arguments" +
                    "\njava HotelClient list\n" +
                    "java HotelClient book\n" +
                    "java HotelClient guests");
        }

        if (args.length > 0){
            switch (args[0]){
                case "list":
                    list_rooms();
                    break;
                case "book":
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Please enter your name: ");
                    String name = scanner.next();
                    System.out.print("Please enter the type of room you want. Choose integer betweeen 0 and 4: ");
                    int type = scanner.nextInt();
                    boolean status = stub.book(type, name);
                    if (status == true)
                        System.out.println("Success: Room reserved !");
                    else
                        System.out.println("Failed: Room cannot be reserrved ! Try another one");
                    break;
                case "guests":
                    print_guests();
                    break;
            }
        }

    }
}
