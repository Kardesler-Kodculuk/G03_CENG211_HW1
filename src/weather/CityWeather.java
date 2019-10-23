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
	
	public static Weather[] returnWeatherArray(CityWeather[] cityWeatherArray) {
		Weather[] weatherArray = new Weather[cityWeatherArray.length];
		for (int i = 0; i < cityWeatherArray.length; i++) {
			weatherArray[i] = cityWeatherArray[i].getWeather();
		}
		return weatherArray;
	}
}
