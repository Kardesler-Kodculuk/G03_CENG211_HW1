package weather;
import geography.City;
public class CityWeather {
	private City city;
	private Weather weather;
	private String date;
	
	public CityWeather(City city, Weather weather, String date) {
		this.city = city;
		this.weather = weather;
		this.date = date;
	}
	
	public City getCity() {
		return city;
	}
	public Weather getWeather() {
		return weather;
	}
	public String getDate() {
		return date;
	}
	
	/**
	 * Return an array of temperatures from a given array of cityWeathers
	 * @param cityWeatherArray cityWeather array
	 * @return an array of tempreatures as doubles.
	 */
	public static double[] getTemperatureArray(CityWeather[] cityWeatherArray) {
		double[] weatherArray = new double[cityWeatherArray.length];
		for (int i = 0; i < cityWeatherArray.length; i++) {
			weatherArray[i] = cityWeatherArray[i].getWeather().getTemperature();
		}
		return weatherArray;
	}
}
