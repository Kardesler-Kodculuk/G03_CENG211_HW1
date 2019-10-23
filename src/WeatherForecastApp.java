import geography.Region;
import utility.*;
import weather.CityWeather;
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
		CityWeather[][] weeklyForecast = FileIO.returnWeeklyForecast("weather.csv");
		WeatherQuerry weatherQuerry = new WeatherQuerry(regions, weeklyForecast);
	}

}
