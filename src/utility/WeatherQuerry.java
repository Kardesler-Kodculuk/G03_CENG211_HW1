package utility;

import geography.City;
import geography.Region;
import weather.CityWeather;
import weather.Weather;

public class WeatherQuerry {
	private final int CITYCOUNT;
	private final int REGIONCOUNT;
	private CityWeather[][] weatherForecast;
	private Region[] regions;
	
	public WeatherQuerry(Region[] regions, CityWeather[][] weatherForecast) {
		this.regions = regions;
		this.weatherForecast = weatherForecast;
		this.CITYCOUNT = weatherForecast.length;
		this.REGIONCOUNT = regions.length;
	}
	
	private void printLowestFeelLikeTemperature() {
		CityWeather cityWeathers[] = ArrayHelpers.straighten(weatherForecast);
		double lowestFeelsLike = 0;
		double currentFeelsLike = 0;
		City[] resultCities = new City[CITYCOUNT];
		int emptyIndex = 0;
		for (CityWeather currentCityWeather : cityWeathers) {
			if (currentCityWeather == null) {
				continue;
			}
			currentFeelsLike = currentCityWeather.getWeather().getFeelsLikeTemperature();
			if (currentFeelsLike == lowestFeelsLike) {
				resultCities = ArrayHelpers.ensureCapacity(resultCities);
				resultCities[emptyIndex++] = currentCityWeather.getCity();
			} else if (currentFeelsLike < lowestFeelsLike) {
				ArrayHelpers.formatArray(resultCities);
				emptyIndex = 1;	
				resultCities[emptyIndex] = currentCityWeather.getCity();
			}
		}
		System.out.println(ArrayHelpers.trimArrayToFullFilled(resultCities));
	}

	private void printTopThreeCitiesWithTheHighestTemperatureVariation() {
		City[] cities = new City[CITYCOUNT];
		Double[] temperatureVariations = new Double[CITYCOUNT];
		int emptyIndex = 0;
		double temperatureVariation = 0;
		double[] weeklyTemperature;
		for (CityWeather[] cwArray : weatherForecast) {
			if (cwArray.length == 0) {
				continue;
			}
			weeklyTemperature = CityWeather.getTemperatureArray(cwArray);
			temperatureVariation = ArrayHelpers.findMinMaxDifference(weeklyTemperature);
			temperatureVariations[emptyIndex] = temperatureVariation;
			cities[emptyIndex] = cwArray[0].getCity();
		}
		ArrayHelpers.sortArrayAccordingTo(cities, temperatureVariations);
		System.out.println(cities[CITYCOUNT - 1].toString() + cities[CITYCOUNT - 2].toString() +  cities[CITYCOUNT - 3].toString());
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
		ArrayHelpers.sortArrayAccordingTo(resultRegions, avarageHumidities);
		System.out.println(resultRegions[REGIONCOUNT - 1]);
		
	}
	
	private void printMeanTemperatureAltitude () {
		double lowestAltitude = 0, highestAltitude = 0, currentAltitude;
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
	public void ask() {
		printLowestFeelLikeTemperature();
		printTopThreeCitiesWithTheHighestTemperatureVariation();
		printRegionWithHighestHumidity();
	}
}
