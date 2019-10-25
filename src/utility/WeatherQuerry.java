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
		ArrayHelpers.prettyPrintArray(ArrayHelpers.trimArrayToFullFilled(resultCities));
	}

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
			temperatureVariation = ArrayHelpers.findMinMaxDifference(weeklyTemperature);
			temperatureVariations[emptyIndex] = temperatureVariation;
			cities[emptyIndex++] = cwArray[0].getCity();
		}
		cities = ArrayHelpers.trimArrayToFullFilled(cities);
		temperatureVariations = ArrayHelpers.trimArrayToFullFilled(temperatureVariations);
		ArrayHelpers.sortArrayAccordingTo(cities, temperatureVariations);
		System.out.println(cities[cities.length - 1].toString() + ", " + cities[cities.length - 2].toString() +  ", " + cities[cities.length - 3].toString());
	}
	
	private void printRegionWithHighestHumidity () {
		double[] totalHumidities = new double[REGIONCOUNT];
		Double[] avarageHumidities = new Double[REGIONCOUNT];
		Region[] resultRegions = regions.clone();
		int regionIndex;
		for (int i = 1; i < weatherForecast.length; i++) {
			for (Region region : regions) {
				if (ArrayHelpers.isReferenceInArray(weatherForecast[i][0].getCity(), region.getCities())) {
					regionIndex = ArrayHelpers.returnIndexByReference(region, regions);
					for (int j = 0; j < weatherForecast[i].length; j++) {
						totalHumidities[regionIndex] += weatherForecast[i][j].getWeather().getHumidity();
					}
					totalHumidities[regionIndex] /= 7;
				}
			}
		}
		for (int j = 0; j < totalHumidities.length; j++) {
			avarageHumidities[j] = totalHumidities[j] / regions[j].getCities().length;
		}
		ArrayHelpers.sortArrayAccordingTo(resultRegions, avarageHumidities);
		System.out.println(resultRegions[REGIONCOUNT - 1]);
		
	}
	
	private void printMeanTemperatureAltitude () {
		double firstAltitude = weatherForecast[1][0].getCity().getAltitude();
		double lowestAltitude = firstAltitude , highestAltitude = firstAltitude, currentAltitude;
		int lowestIndex = 0, highestIndex = 0;
		for (int i = 1; i <= CITYCOUNT; i++) {
			currentAltitude = weatherForecast[i][0].getCity().getAltitude();
			if (currentAltitude > highestAltitude) {
				highestAltitude = currentAltitude;
				highestIndex = i;
			} else if (currentAltitude < lowestAltitude) {
				lowestAltitude = currentAltitude;
				lowestIndex = i;
			}
		}
		System.out.println(ArrayHelpers.calculateMean(CityWeather.getTemperatureArray(weatherForecast[lowestIndex])) + ", " + ArrayHelpers.calculateMean(CityWeather.getTemperatureArray(weatherForecast[highestIndex])));
	}
	
	private void printRainyDays() {
		City[] cityArray = new City[10];
		int index = 0;
		for (int i = 1; i < weatherForecast.length; i++) {
			if (weatherForecast[i][0].getWeather().getPrecipetion() >= RAINTHRESHOLD && weatherForecast[i][1].getWeather().getPrecipetion() >= RAINTHRESHOLD) {
				cityArray = ArrayHelpers.ensureCapacity(cityArray);
				cityArray[index++] = weatherForecast[i][0].getCity();
			}
		}
		cityArray = ArrayHelpers.trimArrayToFullFilled(cityArray);
		ArrayHelpers.prettyPrintArray(cityArray);
	}
	
	private boolean isFlyable(CityWeather cityWeather) {
		return cityWeather.getWeather().getWind() < 40 && cityWeather.getWeather().getVisibilty().ordinal() > 1;
	}
	
	private void printFlightableDays() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter a city name to see its flyable days:\n> ");
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
		ArrayHelpers.prettyPrintArray(dates);
	}
	public void ask() {
		printLowestFeelLikeTemperature();
		printTopThreeCitiesWithTheHighestTemperatureVariation();
		printRegionWithHighestHumidity();
		printMeanTemperatureAltitude();
		printRainyDays();
		printFlightableDays();
	}
}
