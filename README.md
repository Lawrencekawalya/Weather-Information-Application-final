
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

      
    git clone https://github.com/Lawrencekawalya/Weather-Information-Application-final.git
    

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
![takyo](https://github.com/Lawrencekawalya/Weather-Information-Application-final/assets/117585199/3b71bf20-9abc-4406-a7b0-1ffac164320e)
![los](https://github.com/Lawrencekawalya/Weather-Information-Application-final/assets/117585199/274e0699-a434-47cf-b309-ab99abfadb78)
![Capture(4)](https://github.com/Lawrencekawalya/Weather-Information-Application-final/assets/117585199/114f7d33-6b02-4654-a0b1-3a315e61d521)
![history](https://github.com/Lawrencekawalya/Weather-Information-Application-final/assets/117585199/c62b2a0a-4d97-4035-bc5d-e7e6ac6e4d1a)
![Capture(10)](https://github.com/Lawrencekawalya/Weather-Information-Application-final/assets/117585199/588a0954-e83a-444d-90cb-a47eab1b5a32)
