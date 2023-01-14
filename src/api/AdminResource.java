package api;

import model.customer.Customer;
import model.room.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Tigist
 */
public class AdminResource {
    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    private AdminResource() {
    }

    private static class singletonHelper {
        private static final AdminResource INSTANCE = new AdminResource();
    }

    public static AdminResource getInstance() {
        return AdminResource.singletonHelper.INSTANCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
