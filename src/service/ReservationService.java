package service;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import repository.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
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
        if (Repository.rooms.stream().anyMatch(r -> r.getRoomNumber() == room.getRoomNumber())) {
            System.out.println("Room already exist.");
            return;
        }
        Repository.rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
            for (IRoom obj : Repository.rooms) {
                if (obj.getRoomNumber().equals(roomId)) {
                    return obj;
                }
            }
        return null;
    }

    public Collection<IRoom> getAllRooms() {
        return Repository.rooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        //check the room is reserved or not
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        System.out.println(newReservation);
        Repository.reservations.add(newReservation);
        return newReservation;
    }

    public Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return searchForAvailableRooms(checkInDate, checkOutDate);
    }

    List<IRoom> searchForAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        List<IRoom> unavailableRooms = Repository.reservations.stream().filter(reservation -> checkOutDate.isAfter(reservation.getCheckingDate()) && checkInDate.isBefore(reservation.getCheckoutDate())).map(Reservation::getRoom).collect(Collectors.toList());

        List<IRoom> availableRooms = Repository.rooms.stream().filter(room -> unavailableRooms.stream().noneMatch(unavailableRoom -> unavailableRoom.equals(room))).collect(Collectors.toList());
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
}
