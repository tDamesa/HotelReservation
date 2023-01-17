package model.room;

import java.util.Objects;

/**
 * @author Tigist
 */
public class Room implements IRoom {


    private final String roomNumber;
    private final double roomPrice;
    private final RoomType roomType;


    public Room(String roomNumber, double roomPrice, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return this.roomPrice == 0;
    }

    @Override
    public String toString() {
        return "Room: " + "Room Number: " + roomNumber + ", " + "Room Price: " + roomPrice + ", " + "Room Type: " + roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        return ((Room) o).roomNumber.equals(roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
