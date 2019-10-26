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
	
	
	
	public byte getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public int getIndex() {
		return index;
	}



	public void addCity(City city) {
		cities = ArrayHelpers.ensureCapacity(cities);
		cities[index] = city;
		index++;
	}
	public City[] getCities() {
		return cities;
	}
	public String toString() {
		return name;
	}
}
