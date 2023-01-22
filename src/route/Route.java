package route;

public class Route {
//    name of destination
    public String destination;
    public String arrivalTime;
    public String departureTime;

//    time of break in minutes
    public Integer breakTime;

    public Route(String destination, String arrivalTime, String departureTime, Integer breakTime) {
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.breakTime = breakTime;
    }

    @Override
    public String toString() {
        return "_______________\nDestination place: " + destination + "\nArrival time: "
                + arrivalTime + "\nBreak time: " + breakTime + " minutes" + "\nDeparture time: " + departureTime
                + "\n_______________\n";
    }
}
