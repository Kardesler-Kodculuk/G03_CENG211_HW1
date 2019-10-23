package weather;
import geography.City;
public class CityWeather {
	private City city;
	private Weather weather;
	private String date;
	
	public City getCity() {
		return city;
	}
	public Weather getWeather() {
		return weather;
	}
	public String getDate() {
		return date;
	}
	
	public static double[] getTemperatureArray(CityWeather[] cityWeatherArray) {
		double[] weatherArray = new double[cityWeatherArray.length];
		for (int i = 0; i < cityWeatherArray.length; i++) {
			weatherArray[i] = cityWeatherArray[i].getWeather().getTemperature();
		}
		return weatherArray;
	}
}
