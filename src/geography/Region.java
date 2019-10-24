package geography;
import utility.ArrayHelpers;

public class Region {
	private final byte id;
	private final String name;
	private City[] cities = new City[10];
	int index = 0;

	public Region(byte id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void addCity(City city) {
		ArrayHelpers.ensureCapacity(cities);
		cities[index] = city;
	}
	public City[] getCities() {
		return cities;
	}
}
