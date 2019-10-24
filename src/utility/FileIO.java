package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import geography.City;
import geography.Region;
import weather.CityWeather;
import weather.Weather;

public class FileIO {
	private static String[] parseFile(String fileName) {
		BufferedReader br;
		String[] lineArray = new String[5];
		int i = 0;
		String line;
		try {
			br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				ArrayHelpers.ensureCapacity(lineArray);
				lineArray[i] = line;
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("An I/O Error Happened");
		} 
		return lineArray;
		}
	
	public static City[] returnCities(String cityFile, Region[] regions) {
		String[] lines = FileIO.parseFile(cityFile);
		City[] cities = new City[82];
		Region region = null;
		for (String line: lines) {
			String[] values = line.split(","); //TODO there could be an error
			byte plateNo = Byte.parseByte(values[0]);
			String name = values[1];
			byte regionID = Byte.parseByte(values[2]);
			region = regions[regionID - 1];
			double altitude = Double.parseDouble(values[4]);
			City city = new City(plateNo, name, regionID, region, altitude);
			city.getRegion().addCity(city);
			cities[plateNo] = city;
		}
		return cities;
	}
	public static CityWeather[][] returnWeeklyForecast(String weatherFile, City[] cities) {
		CityWeather[][] weeklyForecast = new CityWeather[7][82];
		String[] lines = FileIO.parseFile(weatherFile);
		for(int i = 0; i < lines.length; i++) {
			String[] values = lines[i].split(",");
			double wind = (double) Double.parseDouble(values[2]);
			double temperature = (double) Double.parseDouble(values[3]);
			double feelsLikeTemperature = (double) Double.parseDouble(values[4]);
			double humidity = (double) Double.parseDouble(values[5]);
			double precipetion = (double) Double.parseDouble(values[6]);
			Weather.Visibility visibility = Weather.Visibility.valueOf(values[7].toUpperCase());
			Weather weather = new Weather(wind, temperature, feelsLikeTemperature, humidity, precipetion, visibility);
			int plateNo = Integer.parseInt(values[0]);
			City city = cities[plateNo];
			String date = values[1];
			CityWeather oneDayCityWeather = new CityWeather(city, weather, date);
			weeklyForecast[i % 6][plateNo] = oneDayCityWeather;
		}
		return weeklyForecast;
		}
}
