package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import geography.City;
import geography.Region;
import weather.CityWeather;

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
			// For region class identification
			switch (regionID) {
			case 1:
				region = regions[0];
				break;
			case 2:
				region = regions[1];
				break;
			case 3:
				region = regions[2];
				break;
			case 4:
				region = regions[3];
				break;
			case 5:
				region = regions[4];
				break;
			case 6:
				region = regions[5];
				break;
			case 7:
				region = regions[6];
				break;
			}
			double altitude = Double.parseDouble(values[4]);
			City city = new City(plateNo, name, regionID, region, altitude);
			city.getRegion().addCity(city);
			cities[plateNo] = city;
		}
		return cities;
	}
	public static CityWeather[][] returnWeeklyForecast(String weatherFile) {return null;}
}
