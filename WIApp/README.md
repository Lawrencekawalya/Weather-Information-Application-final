
# Weather Information App

The Weather Information App is a Java Swing application that allows users to fetch current weather information and forecasts for a specific city using the OpenWeatherMap API.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Usage](#usage)
- [Implementation Details](#implementation-details)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)

## Features

- Fetch current weather data (temperature, humidity, wind speed) for a specified city.
- Display dynamic background color based on the time of day in the selected city.
- Fetch forecast data for the specified city.
- View search history to track previous searches.
- Error handling for invalid input and failed API requests.

## Requirements

- Java Development Kit (JDK) version 8 or higher.
- Internet connection to fetch data from the OpenWeatherMap API.

## Usage

1. **Clone the Repository**: Clone the repository to your local machine using the following command:

      
    git clone https://github.com/yourusername/weather-information-app.git
    

2. **Compile and Run the Application**: Navigate to the project directory and compile the Java source files. Then, run the application using the following commands:

    
    cd weather-information-app
    javac *.java
    java WeatherInformationApp
    

3. **Enter City Name**: In the application window, enter the name of the city for which you want to fetch weather information.

4. **Fetch Weather**: Click the "Fetch Weather" button to retrieve current weather data for the entered city. The application will display the weather information and dynamically change the background color based on the time of day.

5. **Fetch Forecast**: Optionally, you can click the "Fetch Forecast" button to fetch forecast data for the specified city.

6. **View Search History**: Click the "Search History" button to view a list of previous search entries, including city names, timestamps, and weather data.

## Implementation Details

- The application is implemented in Java using the Swing library for the graphical user interface.
- It makes HTTP GET requests to the OpenWeatherMap API endpoints to fetch weather and forecast data.
- JSON data retrieved from the API responses is parsed using the `org.json` library.
- Error handling is implemented to handle invalid input and failed API requests gracefully.

## Dependencies

- [org.json](https://github.com/stleary/JSON-java): A JSON library for Java that provides support for parsing and manipulating JSON data.

## Contributing

Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue on GitHub or submit a pull request.

## License

This project is not licensed yet.

---