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
	
	public static CityWeather[] getLowestFeelsLikeTemperature(CityWeather[] weathers) {
		CityWeather[] highestWeather = new CityWeather[weathers.length];
		double lowestValue = 0;
		double currentValue = 0;
		int index = 0;
		for (CityWeather cityWeather : weathers) {
			currentValue = cityWeather.getWeather().getFeelsLikeTemperature();
			if (currentValue <= lowestValue) {
				lowestValue = currentValue;
				highestWeather[index] = cityWeather;
				index++;
			}
		}
		return utility.ArrayHelpers.trimArrayToFullFilled(highestWeather);
	}
	
	public static Weather[] returnWeatherArray(CityWeather[] cityWeatherArray) {
		Weather[] weatherArray = new Weather[cityWeatherArray.length];
		for (int i = 0; i < cityWeatherArray.length; i++) {
			weatherArray[i] = cityWeatherArray[i].getWeather();
		}
		return weatherArray;
	}
}
