package model.room;
/**
 *
 * @author Tigist
 */
public interface IRoom {

    String getRoomNumber();

    double getRoomPrice();

    RoomType getRoomType();

    boolean isFree();

}
