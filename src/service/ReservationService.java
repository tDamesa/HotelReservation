package service;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import repository.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tigist
 */
public class ReservationService {

    private ReservationService() {
    }

    private static class SingletonHelper {
        private static final ReservationService INSTANCE = new ReservationService();
    }

    public static ReservationService getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void addRoom(IRoom room) {
        if (Repository.rooms.containsKey(room.getRoomNumber())) {
            System.out.println("Room already exist.");
            return;
        }
        Repository.rooms.put(room.getRoomNumber(), room);
        System.out.println(room);
    }

    public IRoom getARoom(String roomNumber) {
        return Repository.rooms.get(roomNumber);
    }

    public Collection<IRoom> getAllRooms() {
        return new ArrayList<IRoom>(Repository.rooms.values());
    }

    public Reservation reserveARoom(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        if (getUnavailableRooms(checkInDate, checkOutDate).stream().anyMatch(r -> r.getRoomNumber().equals(room.getRoomNumber()))) {
            System.out.println("Room unavailable on the entered dates.");
            return null;
        }
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        System.out.println(newReservation);
        Repository.reservations.add(newReservation);
        return newReservation;
    }

    public Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return searchForAvailableRooms(checkInDate, checkOutDate);
    }

    List<IRoom> searchForAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        Collection<IRoom> unavailableRooms = getUnavailableRooms(checkInDate, checkOutDate);

        List<IRoom> availableRooms = Repository.rooms.values().stream().filter(room -> unavailableRooms.stream().noneMatch(unavailableRoom -> unavailableRoom.equals(room))).collect(Collectors.toList());
        return availableRooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        return Repository.reservations.stream().filter(obj -> obj.getCustomer().equals(customer)).collect(Collectors.toList());
    }

    public void printAllReservation() {
        if (Repository.reservations.isEmpty()) {
            System.out.println("No reservation found.");
        } else
            Repository.reservations.stream().forEach(System.out::println);
    }

    public Collection<IRoom> getUnavailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return Repository.reservations.stream().filter(reservation -> checkOutDate.isAfter(reservation.getCheckingDate()) && checkInDate.isBefore(reservation.getCheckoutDate())).map(Reservation::getRoom).collect(Collectors.toList());

    }
}
