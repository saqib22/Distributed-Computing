/*
 * Author : Saqib Ali Khan
 * BSCS6C
 * SEECS-NUST
 * */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface HotelReservation extends Remote {
    public int prices[] = {55, 75, 80, 150, 230};
    public int rooms_available[] = {10, 20, 5, 3, 2};
    public ArrayList<Guest> guests= new ArrayList<Guest>();

    boolean book(int type, String name) throws RemoteException;
    int[] list() throws RemoteException;
    ArrayList<Guest> guests() throws RemoteException;
    int[] getPrices() throws RemoteException;
}
