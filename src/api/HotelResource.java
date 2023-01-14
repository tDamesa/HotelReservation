package api;

import model.reservation.Reservation;
import model.room.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author Tigist
 */
public class HotelResource {
    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    private HotelResource() {
    }

    private static class singletonHelper {
        private static final HotelResource INSTANCE = new HotelResource();
    }

    public static HotelResource getInstance() {
        return singletonHelper.INSTANCE;
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getARoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        return reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        return reservationService.getCustomerReservation(customerService.getCustomer(customerEmail));
    }

    public Collection<IRoom> findRooms(LocalDate checkIn, LocalDate checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

}
