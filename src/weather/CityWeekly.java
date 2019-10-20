package weather;

import geography.City;

public class CityWeekly {
	/*
	 * An intermediate form object used to tie a city to its corresponding cityWeather objects.
	 * */
	private CityWeather[] weeklyWeathers;
	private City city;
	
	public CityWeekly(CityWeather[] weeklyWeathers) {
		this.city = weeklyWeathers[0].getCity();
		this.weeklyWeathers = weeklyWeathers;
	}
	
	/*
	 * Return the CityWeather objects corresponding to the day with lowest feel-like temperature in Celcius.
	 */
	public CityWeather getLowestFeelLikeTemperatureCityWeather() {
		return CityWeather.getLowestFeelsLikeTemperature(weeklyWeathers)[0];
	}
}
