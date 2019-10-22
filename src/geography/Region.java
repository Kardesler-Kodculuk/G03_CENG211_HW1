package geography;
import utility.ArrayHelpers;

public class Region {
	private final byte id;
	private final String name;
	private City[] cities = new City[10];

	public Region(byte id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void addCity(City city) {
		ArrayHelpers.ensureCapacity(cities);
		cities[city.getPlateNo()] = city;
	}
	public City[] getCities() {
		return cities;
	}
}
