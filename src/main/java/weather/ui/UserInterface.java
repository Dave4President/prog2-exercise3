package weather.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;
import weather.ctrl.WeatherController;

public class UserInterface {

	private WeatherController ctrl = new WeatherController();
	private double longitude;
	private double latitude;

	public void getWeatherForVienna(){

		longitude = 16;
		latitude = 48;

		String forecastUrlString = "https://api.darksky.net/forecast/##key##/##latitude##,##longitude####time##";

		//Tenter the coordinates

		//Geo Koord. Wien
		//Breitengrad von Wien	48.208174
		//Längengrad von Wien	16.373819
		// https://api.darksky.net/forecast/##key##/##latitude##,##longitude####time##

		//ForecastRequestBuilder.location(48, 16);

		Longitude longitude = new Longitude(48);
		Latitude latitude = new Latitude(16);

		GeoCoordinates Location1 = new GeoCoordinates(longitude, latitude);

		ctrl.process(Location1);

	}

	public void getWeatherForLosAngeles(){


		//enter the coordinates
		//done

		longitude = 41;
		latitude = 12;

		Longitude longitude = new Longitude(41);
		Latitude latitude = new Latitude(12);

		GeoCoordinates Location2 = new GeoCoordinates(longitude, latitude);

		ctrl.process(Location2);
		//Rom 41 N / 12 W
		//Breitengrad von Los Angeles:
		//34.1139
		//Längengrad von Los Angeles:
		//-118.4068
		//GPS Koordinaten von Los Angeles:
		//34° 6‘ 50.04 N 118° 24‘ 24.48 W
	}

	public void getWeatherForRome(){

		//enter the coordinates
		//done

		longitude = 139;
		latitude = 35;

		Longitude longitude = new Longitude(139);
		Latitude latitude = new Latitude(35);

		GeoCoordinates Location3 = new GeoCoordinates(longitude, latitude);

		ctrl.process(Location3);

		//Tokio
		//Breitengrad von Tokio:
		//35.6897
		//Längengrad von Tokio:
		//139.6922
		//GPS Koordinaten von Tokio:
		//35° 41‘ 22.92 N 139° 41‘ 31.92 E

	}
	
	public void getWeatherByCoordinates() {
		//read the coordinates from the cmd
		//Tenter the coordinates

		// Es macht leider nicht was es tun soll.. Es werden nicht die Werte ausgegeben, die mit der direkten Stadtauswahl ausgegeben werden.
		// longitude und latitude kommen laut Debug nicht im WeatherController an.

		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));

		Scanner scanner = new Scanner(System.in);

		System.out.println("Give Longitude: ");
		Longitude longitude = new Longitude(readDouble(-180, 180));
		System.out.println("Give Latitude: ");
		Latitude latitude = new Latitude(readDouble(-90, 90));

		GeoCoordinates Location4 = new GeoCoordinates(longitude, latitude);
		ctrl.process(Location4);
	}

	public void start() {
		Menu<Runnable> menu = new Menu<>("Weather Infos");
		menu.setTitel("Wählen Sie eine Stadt aus:");
		menu.insert("a", "Vienna", this::getWeatherForVienna);
		menu.insert("b", "Los Angeles", this::getWeatherForLosAngeles);
		menu.insert("c", "Rome", this::getWeatherForRome);
		menu.insert("d", "City via Coordinates:",this::getWeatherByCoordinates);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit)
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
