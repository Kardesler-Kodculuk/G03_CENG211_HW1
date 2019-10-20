package utility;

import geography.City;
import geography.Region;
import weather.CityWeather;
import weather.CityWeekly;
import weather.RegionWeekly;

public class WeatherQuerry {
	private static final int cityCount = 81;
	private static final int regionCount = 7;
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
	
	public void printLowestFeelLikeTemperature() {
		CityWeather[][] results = new CityWeather[regionWeeklyForecasts.length][];
		CityWeather parsedResults[];
		for (int i = 0; i < regionWeeklyForecasts.length; i++) {
			results[i] = regionWeeklyForecasts[i].getLowestFeelLikeTemperatureCityWeather();
		}
		parsedResults = utility.ArrayHelpers.straighten(results);
		CityWeather[] lowestTemperatures = CityWeather.getLowestFeelsLikeTemperature(parsedResults);
		System.out.println(lowestTemperatures);
		
	}

	public void ask(Region[] regions, CityWeather[][] cityWeather) {
		
	}
}
