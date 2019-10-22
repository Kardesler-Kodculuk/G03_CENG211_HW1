package geography;

public class City {
	private byte plateNo;
	private String name;
	private byte regionID;
	private Region region;
	private double altitude;
	
	public City(byte plateNo, String name, byte regionID, Region region, double altitude) {
		this.plateNo = plateNo;
		this.name = name;
		this.regionID = regionID;
		this.region = region;
		this.altitude = altitude;
	}
	
	public byte getPlateNo() {
		return plateNo;
	}
	public String getName() {
		return name;
	}
	public byte getRegionID() {
		return regionID;
	}
	public Region getRegion() {
		return region;
	}
	public double getAltitude() {
		return altitude;
	}
}
