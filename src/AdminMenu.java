import api.AdminResource;
import common.Helper;
import model.room.IRoom;
import model.room.Room;
import model.room.RoomType;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tigist
 */
public class AdminMenu {
    static AdminResource adminResource = AdminResource.getInstance();
    final static Scanner scanner = new Scanner(System.in);

    public static void adminMenu() {
        try {
            while (true) {
                String operation = menu();
                switch (operation) {
                    case "1":
                        printAllCustomers();
                        break;
                    case "2":
                        printAllRooms();
                        break;
                    case "3":
                        printAllReservations();
                        break;
                    case "4":
                        addARoom();
                        break;
                    case "5":
                        addTestData();
                        break;
                    case "6":
                        return;
                    default:
                        menu();
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

    }

    private static void addTestData() {
    }

    private static void addARoom() {
        try {
            System.out.println("Enter room number");
            String roomNumber = addRoomNumber();
            System.out.println("Enter price per night");
            double roomPrice = addRoomPrice(scanner);
            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            RoomType roomType = addRoomType(scanner);

            IRoom room = new Room(roomNumber, roomPrice, roomType);
            adminResource.addRoom(List.of(room));
            System.out.println(room);

            System.out.println("Would you like to add another room y/n");
            addAnotherRoom(scanner);
        } catch (NullPointerException e) {
            System.out.println(e.fillInStackTrace());
        }

    }

    private static String addRoomNumber() {
        return scanner.nextLine();
    }

    private static void addAnotherRoom(Scanner scanner) {
        try {
            String addAnotherRoom = scanner.nextLine();
            switch (addAnotherRoom.toLowerCase()) {
                case "y":
                    addARoom();
                    break;
                case "n":
                    break;
                default:
                    System.out.println("Please enter Y (Yes) or N (No)");
                    addAnotherRoom(scanner);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addAnotherRoom(scanner);
        }
    }

    private static RoomType addRoomType(Scanner scanner) {
        try {
            String roomType = scanner.nextLine();
            switch (roomType) {
                case "1":
                    return RoomType.SINGLE;
                case "2":
                    return RoomType.DOUBLE;
                default:
                    //System.out.println("Please enter 1 or 2 to select room type.");
                    return addRoomType(scanner);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return addRoomType(scanner);
        }
    }

    private static double addRoomPrice(Scanner scanner) {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please add a valid room price.");
            scanner.nextLine();
            return addRoomPrice(scanner);
        }
    }

    static void printAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void printAllRooms() {
        Helper.printGrid(adminResource.getAllRooms(), "Rooms");
    }

    public static void printAllCustomers() {
        Helper.printGrid(adminResource.getAllCustomers(), "Customers");
    }

    public static String menu() {
        System.out.println("Admin Menu \n" +
                "--------------------------------------\n" +
                "1. See all Customers \n" +
                "2. See all Rooms \n" +
                "3. See all Reservations \n" +
                "4. Add a Room \n" +
                "5. Add Test Data \n" +
                "6. Back to Main Menu\n" +
                "--------------------------------------\n" +
                "Please select a number for the menu option");
        return scanner.nextLine();

    }
}
