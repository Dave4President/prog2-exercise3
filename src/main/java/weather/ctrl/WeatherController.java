package weather.ctrl;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.text.DecimalFormat;
import java.util.Locale;

public class WeatherController {
    
    //private String apiKey = "Enter your API key";
    private String apiKey = "ab5c55091bfde0864c41b337f1c66af5";
    private double highestTemp;
    private double averageTemp;
    private double dailyValues;
    private double firstWind;
    private double secondWind;

    

    public void process(GeoCoordinates location) {
        System.out.println("process "+location); //$NON-NLS-1$
		Forecast data = getData(location);

        ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apiKey))
                .location(location)
                .build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();

        //highest tempperature

        System.out.println("Try: Highest Temperature of the last few Days: ");
        try {
            for (int i = 0; i < data.getDaily().getData().size(); i++) {

                System.out.println(data.getDaily().getData().get(i).getTemperatureHigh());
                System.out.println("Aktueller Versuch:");

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Warning: Your Loop us overwhelming");
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Warning: You messed with the data");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //implement Error handling
		
		//implement methods for
		//average temperature -> more Code @ getData()

        System.out.println("The average Temperature of the last 7 Days is: ");
        System.out.println(averageTemp);


        // current temperature

        try {
            Forecast forecast = client.forecast(request);
            System.out.println("Current Temperature: ");
            System.out.println(forecast.getCurrently().getTemperature());
            System.out.println("Highest Temperature: ");
        } catch (ForecastException e) {
            e.printStackTrace();
        }

        //implement a Comparator for the Windspeed
        //implementation is @ getData()

    }
    
    
    public Forecast getData(GeoCoordinates location) {
		ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apiKey))
                .location(location)
                .build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();

        /*try {
            Forecast forecast = client.forecast(request);
            System.out.println("was macht getData?: ");
            System.out.println(forecast.getDaily().getData());
        } catch (ForecastException e) {
            e.printStackTrace();
        }*/

        //client.forecast(getData())
        /*for (int i = 0; i < 8; i++) {

            try {
                Forecast forecast = client.forecast(request);
                tempDbl = forecast.getDaily().getData().get(i).getApparentTemperatureHigh();
                if (tempDbl > highestTemp) {
                    highestTemp = tempDbl;
                }
            } catch (ForecastException e) {
                e.printStackTrace();
                System.out.println("Fail1");
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println("The Index of you Loop is out of Bounds");

            }
        } */

        //Windspeed "comparator":
        try {
            Forecast forecast = client.forecast(request);

            firstWind = forecast.getCurrently().getWindSpeed();
            System.out.println("First Windspeed value: " + firstWind);
            secondWind = forecast.getHourly().getData().get(1).getWindSpeed();
            System.out.println("Second Windspeed value: " + secondWind);

            System.out.println(forecast.getCurrently().getWindSpeed().compareTo(forecast.getHourly().getData().get(0).getWindSpeed()));

            for (int i = 0; i < 7; i++) {
                averageTemp += forecast.getDaily().getData().get(i).getApparentTemperatureHigh();
            }

            averageTemp = averageTemp / 7;
            // Leider bin ich mir nicht sicher welche Temperaturwerte ich hier bekomme, die gibt es im json file nicht.

        } catch (ForecastException e) {
            e.printStackTrace();
        }

        return null;
    }
}
