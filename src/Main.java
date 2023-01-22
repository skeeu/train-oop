import route.Route;
import trains.Train;
import trains.vagons.Kupe;
import trains.vagons.Sitting;
import trains.vagons.Vagon;

import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static JSONParser parser = new JSONParser();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter your username:");
            String username = sc.next();
            System.out.println("Good! Now enter password:");
            String password = sc.next();
            if (login(username, password)) {
                System.out.println("Logged in");
                menu();
                break;
            } else {
                System.out.println("Wrong user\n");
            }
        }
    }

    public static Boolean login(String username, String password) {
        try {
            Object obj = parser.parse(new FileReader("src/users.json"));
            JSONArray usersArray = (JSONArray) obj;
            for(int i = 0; i < usersArray.size(); i++) {
                JSONObject user = (JSONObject) usersArray.get(i);
                if (Objects.equals((String) user.get("username"), username) && Objects.equals((String) user.get("password"), password)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            System.out.println("IO Error" + e);
            return false;
        } catch (ParseException e) {
            System.out.println("Parse Error");
            return false;
        }
    }

    public static void menu () {
        List<Train> trains = readTrains();
        List<String> takenSeats = new ArrayList<>();
        while (true) {
            System.out.println("\nChoose the command: " +
                    "\n1 - Show all trains" +
                    "\n2 - Inspect route of train" +
                    "\n3 - Get full info about train" +
                    "\n4 - Show seats of train" +
                    "\n5 - Take a seat" +
                    "\n6 - My seats" +
                    "\n0 - Exit");

            Integer command = sc.nextInt();

            if (command == 0) {
                break;
            } else if (command == 1) {
                for(Train train: trains) {
                    System.out.println(train);
                }
            } else if (command == 2) {
                System.out.println("Enter ID of Train");
                Integer trainID = sc.nextInt();
                if (trainID < 0 && trainID > trains.size()) {
                    System.out.println("Not valid Train ID");
                } else {
                    trains.get(trainID-1).printRoutes();
                }
            } else if (command == 3) {
                System.out.println("Enter ID of Train");
                Integer trainID = sc.nextInt();
                if (trainID < 0 && trainID > trains.size()) {
                    System.out.println("Not valid Train ID");
                } else {
                    System.out.println(trains.get(trainID-1));
                }
            } else if(command == 4) {
                System.out.println("Enter ID of Train");
                Integer trainID = sc.nextInt();
                if (trainID < 0 && trainID > trains.size()) {
                    System.out.println("Not valid Train ID");
                } else {
                    trains.get(trainID-1).printSeats();
                }
            } else if (command == 5) {
                System.out.println("Enter ID of Train");
                Integer trainID = sc.nextInt();
                if (trainID < 0 && trainID > trains.size()) {
                    System.out.println("Not valid Train ID");
                } else {
                    System.out.println("Enter vagon ID");
                    Integer vagonID = sc.nextInt();
                    System.out.println("Enter seat id. Like '1-1' or '5-9' and etc.");
                    String seatID = sc.next();
                    String result = trains.get(trainID-1).takeSeat(vagonID, seatID);
                    if (result == "You succesfully took a seat") {
                        takenSeats.add("Train ID: " + trainID + ". Vagon ID: " + vagonID + ". Seat ID: " + seatID);
                    }
                    System.out.println(result);
                }
            } else if (command == 6) {
                if (takenSeats.size() > 0) {
                    for(String seat: takenSeats) {
                        System.out.println(seat);
                    }
                } else {
                    System.out.println("You havent ordered seats yet");
                }
            }
            else {
                System.out.println("Enter valid command 0-6\n");
            }
        }
    }

    public static List<Train> readTrains () {
        try {
            Object obj = parser.parse(new FileReader("src/trains.json"));
            JSONObject jsonObject =  (JSONObject) obj;

            JSONArray trainsJSON = (JSONArray) jsonObject.get("trains");

            List<Train> trains = new ArrayList<>();
            for(int i = 0; i < trainsJSON.size(); i++) {
                JSONObject trainJSON = (JSONObject) trainsJSON.get(i);
                JSONArray routesJSON = (JSONArray) trainJSON.get("routes");
                JSONArray vagonsJSON = (JSONArray) trainJSON.get("vagons");

                List<Route> routes = new ArrayList<>();
                List<Vagon> vagons = new ArrayList<>();

                for(int j = 0; j < routesJSON.size(); j++) {
                    JSONObject routeJSON = (JSONObject) routesJSON.get(j);
                    String destination = (String) routeJSON.get("destination");
                    String arrivalTime = (String) routeJSON.get("arrivalTime");
                    String departureTime = (String) routeJSON.get("departureTime");
                    Integer breakTime = ((Long) routeJSON.get("breakTime")).intValue();
                    Route route = new Route(destination, arrivalTime, departureTime, breakTime);
                    routes.add(route);
                }
                for(int j = 0; j < vagonsJSON.size(); j++) {
                    JSONObject vagonJSON = (JSONObject) vagonsJSON.get(j);
                    String vagonType = (String) vagonJSON.get("type");
                    if (Objects.equals(vagonType, "sitting")) {
                       Integer benches = ((Long) vagonJSON.get("benches")).intValue();
                       Integer benchCapacity = ((Long) vagonJSON.get("benchCapacity")).intValue();
                       Sitting vagon = new Sitting(vagonType, benches, benchCapacity, j+1);
                       vagons.add(vagon);
                    } else if (Objects.equals(vagonType, "kupe")) {
                        Integer roomsAmount = ((Long) vagonJSON.get("roomsAmount")).intValue();
                        Integer roomCapacity = ((Long) vagonJSON.get("roomCapacity")).intValue();
                        Kupe vagon = new Kupe(vagonType, roomsAmount, roomCapacity, j+1);
                        vagons.add(vagon);
                    }
                }
                Train train = new Train(routes, vagons, "common");
                trains.add(train);
            }
            return trains;
        } catch (IOException e) {
            System.out.println("IO Error" + e);
            return new ArrayList<>();
        } catch (ParseException e) {
            System.out.println("Parse Error");
            return new ArrayList<>();
        }
    }
}