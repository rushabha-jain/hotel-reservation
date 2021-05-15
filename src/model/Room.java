package model;

import java.util.Objects;

public class Room implements IRoom {
    private final String number;
    private final Double price;
    private final RoomType type;

    public Room(String number, Double price, RoomType type) {
        this.number = number;
        this.price = price;
        this.type = type;
    }

    @Override
    public String getRoomNumber() {
        return number;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return type;
    }

    @Override
    public boolean isFree() {
        return price == 0;
    }

    @Override
    public String toString() {
        return "Room Number: " + number +
                ", price: $" + price +
                ", type: " + (type.equals(RoomType.SINGLE) ? "Single Bed Room" : "Double Bed Room");
    }

    @Override
    public boolean equals(Object o) {
        IRoom room = (IRoom) o;
        return room.getRoomNumber().equals(number) &&
                room.getRoomPrice() == price &&
                room.getRoomType().equals(type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, price, type);
    }
}
