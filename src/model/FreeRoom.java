package model;

public class FreeRoom extends Room {
    public FreeRoom(String number, Double price, RoomType type) {
        super(number, Double.valueOf(0), type);
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "number='" + this.getRoomNumber() + '\'' +
                ", price=" + this.getRoomPrice() +
                ", type=" + this.getRoomType() +
                '}';
    }
}
