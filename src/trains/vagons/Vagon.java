package trains.vagons;

public class Vagon {
    public String type;
    private Integer[][] seats;
    private Integer vagonId;
    public Vagon(String type, Integer[][] seats, Integer vagonId) {
        this.vagonId = vagonId;
        this.type = type;
        this.seats = seats;
    }

    public void printSeats() {
        System.out.println("Vagon type - " + type + ". Vagon id - " + vagonId);
        for(int i = 0; i < seats.length; i++) {
            System.out.print("[ ");
            for(int j = 0; j < seats[i].length; j++) {
                if(seats[i][j] != null) {
                    System.out.print("taken ");
                } else {
                    System.out.print((i+1) + "-" + (j+1) + " ");
                }
            }
            System.out.print("]");
            System.out.print("\n");
        }
    }

    public Integer getCapacity () {
        return 0;
    }

    public Boolean takeSeat(Integer[] seatId) {
        if (seatId[0] <= seats.length && seatId[1] <= seats[0].length) {
            if (seats[seatId[0]-1][seatId[1]-1] == null) {
                seats[seatId[0]-1][seatId[1]-1] = 1;
                return true;
            }
            return false;
        }
        return false;
    }
}
