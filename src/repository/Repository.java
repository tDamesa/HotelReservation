package repository;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Tigist
 */
public class Repository {
    public static HashMap<String, Customer> customers = new HashMap<>();
    public static HashMap<String, IRoom> rooms = new HashMap<>();
    public static List<Reservation> reservations = new ArrayList<>();
}
