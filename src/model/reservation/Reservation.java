package model.reservation;

import model.customer.Customer;
import model.room.IRoom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
/**
 *
 * @author Tigist
 */
public class Reservation {
    private Customer customer;
    private IRoom room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return this.room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public LocalDate getCheckingDate() {
        return this.checkInDate;
    }


    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckoutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    @Override
    public String toString() {
        return  "Reservation Success \n" + getCustomer().getFirstName() + " " + getCustomer().getLastName() +
                "\nRoom: " + getRoom().getRoomNumber() + " - " + getRoom().getRoomType() + " bed\n" + "Price: " + getRoom().getRoomPrice() + " price per night" +
                "\nCheckin Date: " + formatDate(getCheckingDate()) + "\n" + "Checkout Date: " + formatDate(getCheckoutDate());
    }
}
