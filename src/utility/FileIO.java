package utility;

import geography.City;
import geography.Region;
import geography.RegionCityTuple;
import weather.CityWeather;

public class FileIO {
	private static String[] parseFile(String fileName) {return null;}
	private static City[] returnCities(String cityFile, Region[] regions) {
		String[] lines = FileIO.parseFile(cityFile);
		City[] cities;
		for (String line: lines) {
			//;
		}
		return null;
	}
	public static CityWeather[][] returnWeeklyForecast(String weatherFile) {return null;}
}
