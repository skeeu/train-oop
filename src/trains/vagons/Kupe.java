package trains.vagons;

public class Kupe extends Vagon {
    private Integer roomsAmount;
    private Integer roomCapacity;
    public Kupe(String type, Integer roomsAmount, Integer roomCapacity, Integer vagonId) {
        super(type, new Integer[roomsAmount][roomCapacity], vagonId);
        this.roomsAmount = roomsAmount;
        this.roomCapacity = roomCapacity;
    }

    @Override
    public Integer getCapacity () {
        return roomsAmount*roomCapacity;
    }

    public Integer getRoomsAmount() {
        return roomsAmount;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }
}
