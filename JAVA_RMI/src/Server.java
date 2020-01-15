/*
 * Author : Saqib Ali Khan
 * BSCS6C
 * SEECS-NUST
 * */

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements HotelReservation {
    Server() throws RemoteException{
        super();
    }
    @Override
    public boolean book(int type, String name) throws RemoteException{
        if (rooms_available[type] != 0){
            rooms_available[type]--;
            Guest guest = new Guest(name, type);
            guests.add(guest);
            return true;
        }
        return false;
    }

    @Override
    public int[] list() throws RemoteException {
        return rooms_available;
    }

    @Override
    public ArrayList<Guest> guests() throws RemoteException {
        return guests;
    }

    @Override
    public int[] getPrices(){
        return prices;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("HotelReservation", new Server());
            System.out.println("Server Started");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
