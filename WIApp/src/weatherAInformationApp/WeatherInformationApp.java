package weatherAInformationApp;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class represents a Weather Information Application GUI.
 * It fetches weather data from an API based on user input and displays it.
 */
public class WeatherInformationApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField cityTextField;
    private JLabel temperatureLabel_1;
    private JLabel humidityLabel_1;
    private JLabel windSpeedLabel_1;
    private JButton fetchButton;
    private JLabel forecastTemperatureLabel;
    private JLabel forecastWeatherLabel;
    private JButton historyButton;
    private ArrayList<String> searchHistory = new ArrayList<>();
    private JPanel mainPanel; 
    private JLabel timeLabel;

    // Constants for API access
    private static final String API_KEY = "fe1c96083717255b37dfc05cbc6ae755";
    private static final String API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    /**
     * Constructor for the Weather Information App.
     */
    public WeatherInformationApp() {
        setTitle("Weather Information App"); // Set the title of the JFrame
        setSize(620, 340); // Set the size of the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Set default close operation

        // Initialize mainPanel
        mainPanel = new JPanel();
        mainPanel.setForeground(new Color(0, 0, 0));
        mainPanel.setBackground(new Color(0, 0, 0));
        mainPanel.setBorder(new LineBorder(new Color(240, 240, 240), 2));
        mainPanel.setLayout(null); // Set layout to null for absolute positioning

        forecastTemperatureLabel = new JLabel(); // Initialize forecastTemperatureLabel
        forecastWeatherLabel = new JLabel(); // Initialize forecastWeatherLabel

        // Add components to mainPanel
        JLabel label_3 = new JLabel("City:");
        label_3.setForeground(new Color(255, 255, 255));
        label_3.setBackground(new Color(255, 255, 255));
        label_3.setBounds(7, 9, 97, 32); // Set bounds (position and size) of the label
        label_3.setFont(new Font("Times New Roman", Font.BOLD, 14)); // Set font of the label
        label_3.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment
        mainPanel.add(label_3); // Add label to mainPanel

        cityTextField = new JTextField(); // Initialize cityTextField
        cityTextField.setForeground(new Color(0, 0, 0));
        cityTextField.setBounds(103, 9, 145, 32); // Set bounds of the text field
        cityTextField.setBackground(new Color(224, 255, 255));
        cityTextField.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
        mainPanel.add(cityTextField); // Add text field to mainPanel

        fetchButton = new JButton("Fetch Weather"); // Initialize fetchButton
        fetchButton.setBounds(269, 9, 140, 32); // Set bounds of the button
        fetchButton.setFont(new Font("Times New Roman", Font.BOLD, 14)); // Set font of the button
        mainPanel.add(fetchButton); // Add button to mainPanel
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchWeatherData(); // Call method to fetch weather data
                fetchForecastData(); // Call method to fetch forecast data
            }
        });

        // Added History Button
        historyButton = new JButton("Search History"); // Initialize historyButton
        historyButton.setBounds(425, 9, 120, 32); // Set bounds of the button
        historyButton.setFont(new Font("Times New Roman", Font.BOLD, 14)); // Set font of the button
        mainPanel.add(historyButton); // Add button to mainPanel
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySearchHistory(); // Call method to display search history
            }
        });

        JLabel lblWeatherInformation = new JLabel("Weather Information");
        lblWeatherInformation.setHorizontalAlignment(SwingConstants.CENTER);
        lblWeatherInformation.setForeground(new Color(255, 0, 128));
        lblWeatherInformation.setBounds(7, 52, 538, 53);
        lblWeatherInformation.setFont(new Font("Times New Roman", Font.BOLD, 24));
        mainPanel.add(lblWeatherInformation);
        
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setBounds(103, 107, 306, 20);
        mainPanel.add(timeLabel);
        
        // Adding labels for temperature, humidity, and wind speed
        JLabel label = new JLabel("Temperature:");
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(103, 138, 145, 32);
        label.setFont(new Font("Times New Roman", Font.BOLD, 14));
        mainPanel.add(label);

        JLabel label_1 = new JLabel("Humidity:");
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setBounds(269, 138, 140, 32);
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        mainPanel.add(label_1);

        JLabel label_2 = new JLabel("Wind Speed:");
        label_2.setForeground(new Color(255, 255, 255));
        label_2.setBounds(434, 138, 111, 32);
        label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        mainPanel.add(label_2);

        // Labels to display weather information
        temperatureLabel_1 = new JLabel();
        temperatureLabel_1.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\oip (1).png"));
        temperatureLabel_1.setForeground(new Color(255, 255, 255));
        temperatureLabel_1.setBounds(103, 170, 145, 32);
        temperatureLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        mainPanel.add(temperatureLabel_1);

        humidityLabel_1 = new JLabel();
        humidityLabel_1.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\oip (3).png"));
        humidityLabel_1.setForeground(new Color(255, 255, 255));
        humidityLabel_1.setBounds(269, 170, 140, 32);
        humidityLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        mainPanel.add(humidityLabel_1);

        windSpeedLabel_1 = new JLabel();
        windSpeedLabel_1.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\oip (2).png"));
        windSpeedLabel_1.setForeground(new Color(255, 255, 255));
        windSpeedLabel_1.setBounds(434, 170, 111, 32);
        windSpeedLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        mainPanel.add(windSpeedLabel_1);

        JLabel lblForecast = new JLabel("Forecast");
        lblForecast.setHorizontalAlignment(SwingConstants.CENTER);
        lblForecast.setForeground(new Color(255, 0, 128));
        lblForecast.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblForecast.setBounds(10, 197, 587, 53);
        mainPanel.add(lblForecast);

        forecastTemperatureLabel = new JLabel();
        forecastTemperatureLabel.setForeground(new Color(255, 255, 255));
        forecastTemperatureLabel.setBounds(175, 245, 135, 32);
        forecastTemperatureLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        mainPanel.add(forecastTemperatureLabel);

        forecastWeatherLabel = new JLabel();
        forecastWeatherLabel.setForeground(new Color(255, 255, 255));
        forecastWeatherLabel.setBounds(320, 245, 225, 32);
        forecastWeatherLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        mainPanel.add(forecastWeatherLabel);

        getContentPane().add(mainPanel); // Add mainPanel to the content pane
        setVisible(true); // Make the JFrame visible
    }


    /**
     * Method to fetch weather data from the API based on the user input city.
     * If the city is not empty, it fetches the weather data and updates the GUI components accordingly.
     * If there's an error fetching data, it displays an error message.
     */
    private void fetchWeatherData() {
        String city = cityTextField.getText().trim();
        if (!city.isEmpty()) {
            try {
                String weatherData = getWeatherData(city); // Fetch weather data from API
                updateWeatherLabels(weatherData, mainPanel); // Update GUI with weather data
                fetchButton.setBackground(Color.GREEN); // Set fetch button background to green
                addToSearchHistory(city, weatherData); // Add city and weather data to search history
            } catch (IOException e) {
                fetchButton.setBackground(Color.RED); // Set fetch button background to red in case of error
                JOptionPane.showMessageDialog(this, "Error fetching weather data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Display error message
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a city name", "Error", JOptionPane.ERROR_MESSAGE); // Display error message if city field is empty
        }
    }

    /**
     * Method to update weather information labels with retrieved data from the weather API.
     * It parses the JSON weather data, extracts relevant information, and updates the GUI components accordingly.
     * Additionally, it determines the local time of the city based on the timezone offset and updates the background color
     * of the main panel based on the time of day.
     *
     * @param weatherData The JSON string containing weather information for the city.
     * @param mainPanel   The main panel of the GUI where weather information is displayed.
     */
    private void updateWeatherLabels(String weatherData, JPanel mainPanel) {
        // Parse JSON weather data
        JSONObject json = new JSONObject(weatherData);
        JSONObject main = json.getJSONObject("main");

        // Extract weather information
        double temperature = main.getDouble("temp") - 273.15; // Convert temperature to Celsius
        String formattedTemperature = String.format("%.2f", temperature); // Round temperature to 2 decimal places
        double humidity = main.getDouble("humidity");
        JSONObject wind = json.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");

        // Update weather information labels
        temperatureLabel_1.setText(formattedTemperature + "°C");
        humidityLabel_1.setText(humidity + "%");
        windSpeedLabel_1.setText(windSpeed + "m/s");

        // Get the timezone offset of the city
        int timezoneOffset = json.getInt("timezone");

        // Convert current UTC time to local time of the city
        LocalDateTime cityLocalTime = LocalDateTime.now().plusSeconds(timezoneOffset);

        // Determine time of day based on local time of the city and update main panel background color
        int hour = cityLocalTime.getHour();
        if (hour >= 6 && hour < 12) {
            mainPanel.setBackground(Color.YELLOW); // Morning
            timeLabel.setText("Time range: Morning (6:00 to 11:59)");
        } else if (hour >= 12 && hour < 18) {
            mainPanel.setBackground(Color.ORANGE); // Afternoon
            timeLabel.setText("Time range: Afternoon (12:00 to 17:59)");
        } else if (hour >= 18 && hour < 20) {
            mainPanel.setBackground(Color.RED); // Evening
            timeLabel.setText("Time range: Evening (18:00 to 19:59)");
        } else {
            mainPanel.setBackground(Color.BLUE); // Night
            timeLabel.setText("Time range: Night (20:00 to 5:59)");
        }
    }
  
    
    /**
     * Method to fetch forecast data from the API based on the entered city name.
     * If the city name is empty, it displays an error message.
     */
    private void fetchForecastData() {
        String city = cityTextField.getText().trim();
        if (!city.isEmpty()) {
            try {
                String forecastData = getForecastData(city); // Get forecast data for the city
                updateForecastLabels(forecastData); // Update forecast labels with retrieved data
            } catch (IOException e) {
                // Display error message if fetching forecast data fails
                JOptionPane.showMessageDialog(this, "Error fetching forecast data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Display error message if city name is empty
            JOptionPane.showMessageDialog(this, "Please enter a city name", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Method to make an HTTP request to the weather API and retrieve weather data for the specified city.
     *
     * @param city The name of the city for which weather data is to be retrieved.
     * @return A string containing the weather data retrieved from the API.
     * @throws IOException If an error occurs while making the HTTP request.
     */
    private String getWeatherData(String city) throws IOException {
        // Construct the URL for the weather API request
        String urlString = API_BASE_URL + "?q=" + city + "&appid=" + API_KEY;
        URL url = new URL(urlString);

        // Open connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read response from the connection
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Disconnect the connection
        connection.disconnect();

        // Return the retrieved weather data as a string
        return response.toString();
    }


    /**
     * Method to make an HTTP request to the forecast API and retrieve forecast data for the specified city.
     *
     * @param city The name of the city for which forecast data is to be retrieved.
     * @return A string containing the forecast data retrieved from the API.
     * @throws IOException If an error occurs while making the HTTP request.
     */
    private String getForecastData(String city) throws IOException {
        // Construct the URL for the forecast API request
        String urlString = FORECAST_API_BASE_URL + "?q=" + city + "&appid=" + API_KEY;
        URL url = new URL(urlString);

        // Open connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read response from the connection
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Disconnect the connection
        connection.disconnect();

        // Return the retrieved forecast data as a string
        return response.toString();
    }


    /**
     * Method to update forecast information labels with retrieved forecast data.
     *
     * @param forecastData The forecast data retrieved from the API.
     */
    private void updateForecastLabels(String forecastData) {
        // Parse the JSON string to extract forecast information
        JSONObject json = new JSONObject(forecastData);
        JSONArray list = json.getJSONArray("list");

        // Get the forecast for the next period (e.g., 3 hours from now)
        JSONObject forecast = list.getJSONObject(0);

        // Extract temperature and weather description from the forecast data
        JSONObject main = forecast.getJSONObject("main");
        double temperature = main.getDouble("temp") - 273.15;
        String formattedTemperature = String.format("%.2f", temperature);

        JSONArray weatherArray = forecast.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        String weatherDescription = weather.getString("main");

        // Update the forecast information labels with the retrieved data
        forecastTemperatureLabel.setText("Temperature: " + formattedTemperature + "°C");
        forecastWeatherLabel.setText("Weather: " + weatherDescription);
    }


    /**
     * Method to add search history with weather data.
     *
     * @param city        The city for which weather data is fetched.
     * @param weatherData The weather data retrieved from the API.
     */
    private void addToSearchHistory(String city, String weatherData) {
        // Format timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        // Extract relevant information from the weather data
        JSONObject weatherJson = new JSONObject(weatherData);
        JSONObject mainWeather = weatherJson.getJSONObject("main");
        double temperature = mainWeather.getDouble("temp") - 273.15; // Convert temperature to Celsius
        double humidity = mainWeather.getDouble("humidity");
        JSONObject wind = weatherJson.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");

        // Construct the weather data string
        String weatherInfo = String.format("Temperature: %.2f°C, Humidity: %.2f%%, Wind Speed: %.2f m/s", temperature, humidity, windSpeed);

        // Combine city, timestamp, and weather data
        String entry = String.format("City: %s, Timestamp: %s, Weather Data: %s", city, timestamp, weatherInfo);

        // Add to search history
        searchHistory.add(entry);
    }


    /**
     * Method to display search history.
     */
    private void displaySearchHistory() {
        // Create a StringBuilder to store the search history
        StringBuilder history = new StringBuilder();

        // Iterate through each entry in the search history list
        for (String entry : searchHistory) {
            // Append each entry to the StringBuilder with a newline separator
            history.append(entry).append("\n");
        }

        // Display the search history using a JOptionPane dialog
        JOptionPane.showMessageDialog(this, history.toString(), "Search History", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Main method to run the application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Run the application on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of WeatherInformationApp
                new WeatherInformationApp();
            }
        });
    }

 }
