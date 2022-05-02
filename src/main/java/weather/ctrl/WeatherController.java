package weather.ctrl;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;

public class WeatherController {
    
    //private String apiKey = "Enter your API key";
    private String apiKey = "ab5c55091bfde0864c41b337f1c66af5";
    private double highestTemp;
    private double averageTemp;
    private double dailyValues;

    

    public void process(GeoCoordinates location) {
        System.out.println("process "+location); //$NON-NLS-1$
		Forecast data = getData(location);
		
		//TODO implement Error handling 
		
		//TODO implement methods for
		// highest temperature
		// average temperature 
		// count the daily values
		
		// implement a Comparator for the Windspeed

        //highest temperature:

        System.out.println("Highest temperature the last 14 Days:");
        System.out.println(getData(location));





	}
    
    
    public Forecast getData(GeoCoordinates location) {
		ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apiKey))
                .location(location)
                .build();

        double highestTemp = 0;
        double tempDbl = 0;

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();

        for (int i = 0; i < 9; i++) {

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
        }

        /*try {
            Forecast forecast = client.forecast(request);
            System.out.println(forecast.getDaily().getData().get(1).getApparentTemperatureHigh());
            System.out.println(forecast);
        } catch (ForecastException e) {
            e.printStackTrace();
        }*/
        return null;
    }
}
