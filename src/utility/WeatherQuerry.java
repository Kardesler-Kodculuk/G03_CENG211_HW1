package utility;

import geography.City;
import geography.Region;
import weather.CityWeather;
import weather.CityWeekly;
import weather.RegionWeekly;
import weather.Weather;

public class WeatherQuerry {
	private static final int CITYCOUNT = 81;
	private static final int REGIONCOUNT = 7;
	private CityWeather[][] weatherForecast;
	private RegionWeekly[] regionWeeklyForecasts;
	
	
	public WeatherQuerry(Region[] regions, CityWeather[][] weatherForecast) {
		this.regionWeeklyForecasts = new RegionWeekly[regions.length];
		int i = 0;
		RegionWeekly currentRW;
		for (Region region : regions) {
			regionWeeklyForecasts[i] = new RegionWeekly(region, new CityWeekly[region.getCities().length]);
			i++;
		}
		for (int j = 1; j < weatherForecast.length; j++) {
			currentRW = regionWeeklyForecasts[utility.ArrayHelpers.returnIndexByReference(weatherForecast[i][0].getCity().getRegion(), regions)];
			currentRW.addCityWeekly(new CityWeekly(weatherForecast[i]));
		}
	}
	
	private void printLowestFeelLikeTemperature() {
		CityWeather cityWeathers[] = ArrayHelpers.straighten(weatherForecast);
		double lowestFeelsLike = 0;
		double currentFeelsLike = 0;
		City[] resultCities = new City[CITYCOUNT];
		int emptyIndex = 0;
		for (CityWeather currentCityWeather : cityWeathers) {
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
		Weather[] weeklyWeather;
		double[] weeklyTemperature;
		for (CityWeather[] cwArray : weatherForecast) {
			weeklyWeather = CityWeather.returnWeatherArray(cwArray);
			weeklyTemperature = Weather.getTemperatureArray(weeklyWeather);
			temperatureVariation = ArrayHelpers.findMinMaxDifference(weeklyTemperature);
			temperatureVariations[emptyIndex] = temperatureVariation;
			cities[emptyIndex] = cwArray[0].getCity();
		}
		ArrayHelpers.sortArrayAccordingTo(cities, temperatureVariations);
		System.out.println(cities[CITYCOUNT - 1].toString() + cities[CITYCOUNT - 2].toString() +  cities[CITYCOUNT - 3].toString());
	}
	
	public void ask(Region[] regions, CityWeather[][] cityWeather) {
		printLowestFeelLikeTemperature();
		printTopThreeCitiesWithTheHighestTemperatureVariation();
	}
}
