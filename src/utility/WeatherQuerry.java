package utility;

import java.util.Scanner;

import geography.City;
import geography.Region;
import weather.CityWeather;

public class WeatherQuerry {
	private final int CITYCOUNT;
	private final int REGIONCOUNT;
	private CityWeather[][] weatherForecast;
	private Region[] regions;
	private static final double RAINTHRESHOLD = 80;

	public WeatherQuerry(Region[] regions, CityWeather[][] weatherForecast) {
		this.regions = regions;
		this.weatherForecast = weatherForecast;
		this.CITYCOUNT = weatherForecast.length - 1;
		this.REGIONCOUNT = regions.length;
	}
	
	/**
	 * Print the city with lowest feels like temperature.
	 */
	private void printLowestFeelLikeTemperature() {
		double lowestFeelsLike = weatherForecast[1][0].getWeather().getFeelsLikeTemperature();
		double currentFeelsLike = 0;
		City[] resultCities = new City[CITYCOUNT];
		int emptyIndex = 0;
		for (int i = 1; i < weatherForecast.length; i++) {
			for (int j = 0; j < weatherForecast[i].length; j++) {
				currentFeelsLike = weatherForecast[i][j].getWeather().getFeelsLikeTemperature();
				if (currentFeelsLike == lowestFeelsLike) {
					lowestFeelsLike = currentFeelsLike;
					resultCities[emptyIndex] = weatherForecast[i][j].getCity();
					emptyIndex++;
				} else if (currentFeelsLike < lowestFeelsLike) {
					lowestFeelsLike = currentFeelsLike;
					emptyIndex = 1;
					resultCities = new City[CITYCOUNT];
					resultCities[0] = weatherForecast[i][j].getCity();
				}
			}
		}
		System.out.print("1) ");
		ArrayHelpers.prettyPrintArray(ArrayHelpers.trimArrayToFullFilled(resultCities));
	}

	/**
	 * Print the top three cities with highest temperature variation.
	 */
	private void printTopThreeCitiesWithTheHighestTemperatureVariation() {
		City[] cities = new City[CITYCOUNT];
		Double[] temperatureVariations = new Double[CITYCOUNT];
		int emptyIndex = 0;
		double temperatureVariation = 0;
		double[] weeklyTemperature;
		for (CityWeather[] cwArray : weatherForecast) {
			if (cwArray[0] == null) {
				continue;
			}
			weeklyTemperature = CityWeather.getTemperatureArray(cwArray);
			temperatureVariation = ArrayHelpers.calculateStandartDeviation(weeklyTemperature);
			temperatureVariations[emptyIndex] = temperatureVariation;
			cities[emptyIndex] = cwArray[0].getCity();
			emptyIndex++;
		}
		cities = ArrayHelpers.trimArrayToFullFilled(cities);
		temperatureVariations = ArrayHelpers.trimArrayToFullFilled(temperatureVariations);
		ArrayHelpers.sortArrayAccordingTo(cities, temperatureVariations);
		System.out.print("2) ");
		System.out.println(cities[cities.length - 1].toString() + ", " + cities[cities.length - 2].toString() +  ", " + cities[cities.length - 3].toString());
	}
	
	/**
	 * Print the region with highest average humidity.
	 */
	private void printRegionWithHighestHumidity () {
		double[] totalHumidities = new double[REGIONCOUNT];
		Double[] avarageHumidities = new Double[REGIONCOUNT];
		Region[] resultRegions = regions.clone();
		City[] citiesArray;
		int plateNo = 0;
		for (int i = 0; i < regions.length; i++) {
			citiesArray = regions[i].getCities();
			for (City city : citiesArray) {
				if (city == null) continue;
				plateNo = city.getPlateNo();
				for (CityWeather cityWeather : weatherForecast[plateNo]) {
					totalHumidities[i] += cityWeather.getWeather().getHumidity();
				}
			}
			avarageHumidities[i] = totalHumidities[i] / (7 * ArrayHelpers.trimArrayToFullFilled(regions[i].getCities()).length);
		}
		ArrayHelpers.sortArrayAccordingTo(resultRegions, avarageHumidities);
		System.out.print("3) ");
		System.out.println(resultRegions[REGIONCOUNT - 1]);
		
	}
	
	/**
	 * Print the mean temperatures of the cities with highest and lowest altitude.
	 */
	private void printMeanTemperatureAltitude () {
		double firstAltitude = weatherForecast[1][0].getCity().getAltitude();
		double lowestAltitude = firstAltitude , highestAltitude = firstAltitude, currentAltitude;
		int lowestIndex = 0, highestIndex = 0;
		for (int i = 1; i <= CITYCOUNT; i++) {
			currentAltitude = weatherForecast[i][0].getCity().getAltitude();
			if (currentAltitude >= highestAltitude) {
				highestAltitude = currentAltitude;
				highestIndex = i;
			} else if (currentAltitude <= lowestAltitude) {
				lowestAltitude = currentAltitude;
				lowestIndex = i;
			}
		}
		System.out.print("4) ");
		System.out.println(String.format("%.3f", ArrayHelpers.calculateAvarage(CityWeather.getTemperatureArray(weatherForecast[highestIndex]))) + ", " 
				+ String.format("%.3f", ArrayHelpers.calculateAvarage(CityWeather.getTemperatureArray(weatherForecast[lowestIndex]))));
	}
	
	/**
	 * Print the cities with rainy weather for next two days.
	 */
	private void printRainyDays() {
		City[] cityArray = new City[10];
		int index = 0;
		for (int i = 1; i < weatherForecast.length; i++) {
			if (weatherForecast[i][1].getWeather().getPrecipetion() >= RAINTHRESHOLD && weatherForecast[i][2].getWeather().getPrecipetion() >= RAINTHRESHOLD) {
				cityArray = ArrayHelpers.ensureCapacity(cityArray);
				cityArray[index++] = weatherForecast[i][0].getCity();
			}
		}
		cityArray = ArrayHelpers.trimArrayToFullFilled(cityArray);
		System.out.print("5) ");
		ArrayHelpers.prettyPrintArray(cityArray);
	}
	
	/**
	 * Return if a particular day is flightworthy
	 * @param cityWeather that day's citweather
	 * @return True if flightworthy.
	 */
	private boolean isFlyable(CityWeather cityWeather) {
		return cityWeather.getWeather().getWind() < 40 && cityWeather.getWeather().getVisibilty().ordinal() >= 1;
	}
	
	/**
	 * Return the days that are flightworthy for a specific city.
	 */
	private void printFlightableDays() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("6) Please enter a city name to view flightworthy days:");
		String cityName = userInput.next();
		userInput.close();
		String[] dates = new String[7];
		int emptyIndex = 0;
		int cityIndex = 1;
		while (cityIndex < 82 && !weatherForecast[cityIndex][0].getCity().getName().equals(cityName)) {
			cityIndex++;
		}
		if (cityIndex == 82) {
			System.out.println("Invalid city name entered.");
			return;
		}
		for (CityWeather cityWeather : weatherForecast[cityIndex]) {
			if (isFlyable(cityWeather)) {
				dates[emptyIndex] = cityWeather.getDate();
				emptyIndex++;
			}
		}
		ArrayHelpers.prettyPrintArray(ArrayHelpers.trimArrayToFullFilled(dates));
	}
	
	/**
	 * Print all the queries.
	 */
	public void ask() {
		printLowestFeelLikeTemperature();
		printTopThreeCitiesWithTheHighestTemperatureVariation();
		printRegionWithHighestHumidity();
		printMeanTemperatureAltitude();
		printRainyDays();
		printFlightableDays();
	}
}
