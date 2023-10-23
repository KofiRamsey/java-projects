import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class WeatherApp {
    private static final String API_KEY = "36f56128ae804d70a1f133859230805";
    private static final String API_BASE_URL = "https://api.weatherapi.com/v1/current.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        try {
            String weatherData = fetchWeatherData(location);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(weatherData);

            JsonNode locationNode = jsonNode.get("location");
            JsonNode currentWeatherNode = jsonNode.get("current");

            String cityName = locationNode.get("name").asText();
            String country = locationNode.get("country").asText();
            double temperatureCelsius = currentWeatherNode.get("temp_c").asDouble();
            double temperatureFahrenheit = currentWeatherNode.get("temp_f").asDouble();
            String conditionText = currentWeatherNode.get("condition").get("text").asText();
            int humidity = currentWeatherNode.get("humidity").asInt();

            System.out.println("Key Weather Information for " + cityName + ", " + country + ":");
            System.out.println("Temperature: " + temperatureCelsius + "°C / " + temperatureFahrenheit + "°F");
            System.out.println("Condition: " + conditionText);
            System.out.println("Humidity: " + humidity + "%");
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
        }
    }

    private static String fetchWeatherData(String location) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        String url = API_BASE_URL + "?key=" + API_KEY + "&q=" + location;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
