package repository;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import model.room.Room;
import model.room.RoomType;

import java.util.*;

/**
 *
 * @author Tigist
 */
public class Repository {
     public static List<Customer> customers = new ArrayList<>();
     public static List<IRoom> rooms = new ArrayList<>();
    public static List<Reservation> reservations = new ArrayList<>();
}
