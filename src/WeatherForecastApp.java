import geography.Region;
import utility.*;
import weather.CityWeather;
public class WeatherForecastApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Region[] regions;
		CityWeather[][] weeklyForecast = FileIO.returnWeeklyForecast("weather.csv");
		WeatherQuerry.ask(weeklyForecast, regions);
	}

}
