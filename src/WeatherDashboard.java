import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherDashboard {

    static final String API_KEY = "a60130c1d47506f460b7bf4271cbe7ae";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] cities = {"London", "New York", "Tokyo"};

        while (true) {
            System.out.println("\n=== MORNING ROUTINE WEATHER APP ===");
            System.out.println("1. London");
            System.out.println("2. New York");
            System.out.println("3. Tokyo");
            System.out.println("4. Exit");
            System.out.print("Choose a city: ");

            int choice = scanner.nextInt();

            if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            }

            if (choice >= 1 && choice <= 3) {
                getWeather(cities[choice - 1]);
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    static void getWeather(String city) throws Exception {
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + API_KEY + "&units=imperial";

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner sc = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (sc.hasNext()) {
            response.append(sc.nextLine());
        }
        sc.close();

        // Simple parsing without external libraries
        String data = response.toString();

        String temp = data.split("\"temp\":")[1].split(",")[0];
        String humidity = data.split("\"humidity\":")[1].split(",")[0];
        String description = data.split("\"description\":\"")[1].split("\"")[0];

        System.out.println("\n=== Weather in " + city + " ===");
        System.out.println("Temperature : " + temp + "°F");
        System.out.println("Humidity    : " + humidity + "%");
        System.out.println("Condition   : " + description);
    }
}
