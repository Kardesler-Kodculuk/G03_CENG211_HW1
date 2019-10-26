package weather_forecast_app;
import geography.City;
import geography.Region;
import utility.*;
import weather.CityWeather;


/**
 * G03_CENG211_HW1
 * Oğuzhan Özer 260201039
 * Ege Emir Özkan 260201010
 */
public class WeatherForecastApp {

	public static void main(String[] args) {
		Region akdeniz = new Region((byte) 1, "Akdeniz");
		Region doguAnadolu = new Region((byte) 2, "Doğu Anadolu");
		Region ege = new Region((byte) 3, "Ege");
		Region guneyDogu = new Region((byte) 4, "Güneydoğu Anadolu");
		Region icAnadolu = new Region((byte) 5, "İç Anadolu");
		Region karadeniz = new Region((byte) 6, "Karadeniz");
		Region marmara = new Region((byte) 7, "Marmara");
		
		Region[] regions = {akdeniz, doguAnadolu, ege, guneyDogu, icAnadolu, karadeniz, marmara};
		City[] cities = FileIO.returnCities("CENG211_HW1_Cities.csv", regions);
		CityWeather[][] weeklyForecast = FileIO.returnWeeklyForecast("CENG211_HW1_WeeklyForecast.csv", cities);
		WeatherQuerry weatherQuerry = new WeatherQuerry(regions, weeklyForecast);
		weatherQuerry.ask();
		
	}

}
