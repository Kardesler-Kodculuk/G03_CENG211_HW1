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
	/**
	 * It uses FileReader to read file and BufferedReader to reading lines.
	 * After reading, it puts lines in an array and returns the array. 
	 * @param fileName - Name of the file as a String with its extension.
	 * @return String array which contains every line in the text file.
	 */
	private static String[] parseFile(String fileName) {
		BufferedReader br;
		String[] lineArray = new String[5];
		int i = 0;
		String line;
		try {
			br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				lineArray = ArrayHelpers.ensureCapacity(lineArray);
				lineArray[i] = line;
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return lineArray;
		}
	
	/**
	 * It takes a city file as a string for parsing the cities', creates according to these
	 * attributes and takes a region array to set every city's region to matching ones. It also
	 * fills the regions array with cities. Index of the array starts with 1 which represents
	 * the plate numbers of the cities.
	 * @param cityFile - Name of the file as a String with its extension.
	 * @param regions - Regions array
	 * @return City array according to file
	 */
	public static City[] returnCities(String cityFile, Region[] regions) {
		String[] lines = FileIO.parseFile(cityFile);
		City[] cities = new City[82];
		Region region = null;
		for (String line: lines) {
			if (line == null) {
				continue;
			}
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
	/**
	 * It takes a weather file which has every cities' weekly forecast. Parses the lines.
	 * Creates a double dimensional array. The first arrays represents cities and inside the
	 * city arrays there are seven days of the week with weather forecast. The index 0 is empty
	 * because array filled by plate numbers.
	 * @param weatherFile - Name of the file as a String with its file extension
	 * @param cities - An array holds city objects
	 * @return Two dimensional array. First cities, second days.
	 */
	public static CityWeather[][] returnWeeklyForecast(String weatherFile, City[] cities) {
		CityWeather[][] weeklyForecast = new CityWeather[82][7];
		String[] lines = FileIO.parseFile(weatherFile);
		for(int i = 0; i < lines.length; i++) {
			if (lines[i] == null) {
				continue;
			}
			String[] values = lines[i].split(",");
			double wind = (double) Double.parseDouble(values[2]);
			double temperature = (double) Double.parseDouble(values[3]);
			double feelsLikeTemperature = (double) Double.parseDouble(values[4]);
			double humidity = (double) Double.parseDouble(values[5]);
			double precipetion = (double) Double.parseDouble(values[6]);
			Weather.Visibility visibility = Weather.Visibility.valueOf(values[7].toUpperCase().replace('İ', 'I'));
			Weather weather = new Weather(wind, temperature, feelsLikeTemperature, humidity, precipetion, visibility);
			int plateNo = Integer.parseInt(values[0]);
			City city = cities[plateNo];
			String date = values[1];
			CityWeather oneDayCityWeather = new CityWeather(city, weather, date);
			weeklyForecast[plateNo][i % 7] = oneDayCityWeather;
		}
		return weeklyForecast;
		}
}
