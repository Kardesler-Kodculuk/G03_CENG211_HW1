package weather;

import geography.Region;

/*
 * RegionWeekly class ties a Regions weather to its cities' weathers thus allowing the hierarchical delegation of query methods.
 */
public class RegionWeekly {
	private Region region;
	private CityWeekly[] citiesWeekly;
	private int citiesWeeklyIndex;

	/**
	 * @param region
	 * @param cityWeekly
	 */
	public RegionWeekly(Region region, CityWeekly[] citiesWeekly) {
		this.region = region;
		this.citiesWeekly = citiesWeekly;
	}
	
	/*
	 * Return an array of city or cities with lowest temperature.
	 */
	public CityWeather[] getLowestFeelLikeTemperatureCityWeather() {
		double lowestValue = 0;
		double currentValue = 0;
		CityWeather currentWeather = null;
		CityWeather[] lowestCWs = new CityWeather[citiesWeekly.length];
		int lowestCWsEmptyIndex = 0;
		CityWeather lowestCityWeekly = null;
		for (CityWeekly cityWeekly : citiesWeekly) {
			currentWeather = cityWeekly.getLowestFeelLikeTemperatureCityWeather();
			currentValue = currentWeather.getWeather().getFeelsLikeTemperature();
			if (currentValue <= lowestValue) {
				lowestValue = currentValue;
				lowestCWs[lowestCWsEmptyIndex] = currentWeather;
			}
		}
		return lowestCWs;
	}
	
	/**
	 * Add a city weekly.
	 * @param cityWeekly
	 */
	public void addCityWeekly(CityWeekly cityWeekly) {
		citiesWeekly[citiesWeeklyIndex] = cityWeekly;
		citiesWeeklyIndex++;
	}
	
}
