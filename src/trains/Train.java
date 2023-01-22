package trains;

import route.Route;
import trains.vagons.Vagon;

import java.util.ArrayList;
import java.util.List;

public class Train {
    static Integer id = 0;
    private List<Route> routes;
    private List<Vagon> vagons;
    public Integer maxSpeed;
    public String type;
    public Integer trainId;

    public Train(List<Route> routes, List<Vagon> vagons, String type) {
        id++;
        this.routes = routes;
        this.vagons = vagons;
        this.type = type;
        this.trainId = id;
    }

    public Train(List<Route> routes, List<Vagon> vagons, Integer maxSpeed, String type) {
        this.routes = routes;
        this.vagons = vagons;
        this.maxSpeed = maxSpeed;
        this.type = type;
    }

    public void printRoutes() {
        for (Route route: routes) {
            System.out.println(route);
        }
    }

    public Integer getTotalCapacity () {
        int capacity = 0;
        for(Vagon vagon: vagons) {
            capacity = capacity + vagon.getCapacity();
        }
        return capacity;
    }

    public void printSeats() {
        System.out.println("TRAIN ID: " + trainId);
        for(Vagon vagon: vagons) {
            vagon.printSeats();
        }
        System.out.println("------------");
    }

    public String takeSeat(Integer vagonId, String seatId) {
        if (vagonId <= vagons.size()) {
            String[] splitSeatId = seatId.split("-");
            if (splitSeatId.length == 2) {
                Integer[] rowColumn = new Integer[2];
                for (int i = 0; i < splitSeatId.length; i++) {
                    try {
                        rowColumn[i] = Integer.parseInt(splitSeatId[i]);
                    } catch (NumberFormatException e) {
                        return "Error: " + splitSeatId[i] + " is not a valid integer.";
                    }
                }
                if (rowColumn[0] != null && rowColumn[1] != null) {
                    if (vagons.get(vagonId-1).takeSeat(rowColumn)) {
                        return "You succesfully took a seat";
                    } else {
                        return "This seat was already took. Try to choose other";
                    }
                }
            } else {
                return "Error: not correct seat id. It must be like '1-1' or '5-1' ";
            }
        } else {
            return "Error: Vagon ID is out of range";
        }
        return "Error";
    }

    @Override
    public String toString() {
        String string = "\n\n";
        string = string + "Train ID: " + trainId;
        string = string + "\nRoute: ";
        List<String> list = new ArrayList<>();
        for (Route route: routes) {
            list.add(route.destination);
        }
        string = string + String.join(" -> ", list);
        string = string + "\nCapacity: " + getTotalCapacity();
        string = string + "\nTrain type: " + type;
        return string;
    }
}
