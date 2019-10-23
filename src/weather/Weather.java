package weather;

public class Weather {
	private double wind;
	private double temperature; //Celcius
	private double feelsLikeTemperature; //Celcius
	private double humidity;
	private double precipetion;
	private enum Visibility {LOW, MEDIUM, HIGH}
	private Visibility visibilty;

	public Weather(double wind, double temperature, double feelsLikeTemperature, double humidity, double precipetion,
			Visibility visibilty) {
		super();
		this.wind = wind;
		this.temperature = temperature;
		this.feelsLikeTemperature = feelsLikeTemperature;
		this.humidity = humidity;
		this.precipetion = precipetion;
		this.visibilty = visibilty;
	}

	public double getWind() {
		return wind;
	}

	public double getTemperature() {
		return temperature;
	}

	public double getFeelsLikeTemperature() {
		return feelsLikeTemperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public double getPrecipetion() {
		return precipetion;
	}

	public Visibility getVisibilty() {
		return visibilty;
	}
}
