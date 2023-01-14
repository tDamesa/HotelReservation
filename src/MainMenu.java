import api.AdminResource;
import api.HotelResource;
import common.Helper;
import model.customer.Customer;
import model.room.IRoom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Tigist
 */
public class MainMenu {
    static HotelResource hotelResource = HotelResource.getInstance();
    final static Scanner scanner = new Scanner(System.in);
    public static void mainMenu() {
        try {
            while (true) {
                String operation = menu();
                switch (operation) {
                    case "1":
                        findAndReserveARoom();
                        break;
                    case "2":
                        getReservations();
                        break;
                    case "3":
                        createAccount();
                        break;
                    case "4":
                        AdminMenu.adminMenu();
                        break;
                    case "5":
                        System.out.println("Exiting the application");
                        return;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

    }

    private static String createAccount() {
        try {
            System.out.println("Enter email in format: name@domain.com");
            String customerEmail = scanner.nextLine();
            System.out.println("First name");
            String firstName = scanner.nextLine();
            System.out.println("Last name");
            String lastName = scanner.nextLine();

            hotelResource.createACustomer(customerEmail, firstName, lastName);
            System.out.println("Welcome to the Hotel Reservation Application" + " " + firstName + " " + lastName);
            return customerEmail;
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter a valid email address");
           return createAccount();
        }
    }

    private static void getReservations() {
        try {
            System.out.println("Please enter your email address");
            String email = scanner.nextLine();
            Helper.printGrid(hotelResource.getCustomerReservations(email), "Reservations");
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            mainMenu();
        }
    }

    private static void findAndReserveARoom() {
        try {
            System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/02/2023");
            LocalDate checkIn = dateInput(scanner);
            System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/02/2023");
            LocalDate checkOut = dateInput(scanner);

            if (checkOut.isAfter(checkIn)) {
                LocalDate checkInDate = checkIn;
                LocalDate checkOutDate = checkOut;
                Collection<IRoom> availableRooms = hotelResource.findRooms(checkIn, checkOut);
                if (availableRooms.isEmpty()) {
                    System.out.println("There are no rooms available on the entered dates. Would you like to see alternative rooms y/n?");
                    String getAlternativeRooms = scanner.nextLine().toLowerCase();
                    while (true) {
                        if (!getAlternativeRooms.equals("y") && !getAlternativeRooms.equals("n")) {
                            System.out.println("Please enter Y (yes) or N (no)");
                            continue;
                        }
                        if (getAlternativeRooms.equals("n")) {
                            return;
                        }
                        System.out.println("Please enter the number of days to add to checkIn and Checkout dates");
                        int daysToAdd = scanner.nextInt();
                        checkInDate = checkIn.plusDays(daysToAdd);
                        checkOutDate = checkOut.plusDays(daysToAdd);
                        availableRooms = hotelResource.findRooms(checkIn, checkOut);
                        break;
                    }
                }
                Helper.printGrid(availableRooms, "Rooms");
                if (availableRooms.isEmpty()) {
                    return;
                }
                System.out.println("Would you like to book a room? y/n");
                String bookARoom = scanner.nextLine().toLowerCase();
                if (bookARoom.equals("y")) {
                    bookARoom(checkInDate, checkOutDate);
                } else if (bookARoom.equals("n")) {
                    System.out.println("Returning to menu...");
                } else {
                    System.out.println("Please enter Y (yes) or N (no)");
                }
            } else {
                System.out.println("CheckOut date should be after CheckIn");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please add an integer for days to add");
            findAndReserveARoom();
        }

    }

    private static void bookARoom(LocalDate checkIn, LocalDate checkOut) {
            String email = getOrCreateAccount();
            reserveARoom(checkIn, checkOut, email);
    }

    private static void reserveARoom(LocalDate checkIn, LocalDate checkOut, String email) {
        try {
            System.out.println("What room number would you like to reserve");
            String roomNumber = scanner.nextLine();
            hotelResource.bookARoom(email, hotelResource.getARoom(roomNumber), checkIn, checkOut);
        } catch (NullPointerException e) {
            System.out.println("Room doesn't exist");
            reserveARoom(checkIn, checkOut, email);
        }
    }

    private static String getOrCreateAccount() {
        String email = "";
        System.out.println("Do you have an account with us? y/n");
        String haveAccount = scanner.nextLine().toLowerCase();
        if (haveAccount.equals("y")) {
            System.out.println("Enter email format: name@domain.com");
            email = scanner.nextLine();
            Customer customer = AdminResource.getInstance().getCustomer(email);
            if (customer == null) {
                System.out.println("Sorry, we couldn't find your account. Please create one.");
                email = createAccount();
            }
        } else {
            email = createAccount();
        }
        return email;
    }

    private static LocalDate dateInput(Scanner scanner) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return LocalDate.parse(scanner.nextLine(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please enter date in format mm/dd/yyyy example 02/02/2023");
            findAndReserveARoom();
        }
        return null;
    }

    public static String menu() {
        System.out.println("Main Menu \n" +
                "--------------------------------------\n" +
                "1. Find and reserve a room \n" +
                "2. See my reservations \n" +
                "3. Create an account \n" +
                "4. Admin \n" +
                "5. Exit \n" +
                "--------------------------------------\n" +
                "Please select a number for the menu option");
        return scanner.nextLine();

    }
}
