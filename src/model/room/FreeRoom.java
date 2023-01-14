package model.room;

/**
 *
 * @author Tigist
 */
public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, double roomPrice, RoomType roomType) {
        super(roomNumber, 0, roomType);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
