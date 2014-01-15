package org.scjug.socialjukebox.jukebox;

/**
 * Represents a mappable location belonging to a Jukebox
 */
public class Location {

    private String address;
    private int latitude;
    private int longitude;

    public Location(String address) {
        this.address = address;
        geocode(address);
    }

    public Location(String address, int latitude, int longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * Delegates to a geocoding service to obtain latitude and longitude for the provided address.
     *
     * @param address
     */
    protected void geocode(String address) {
        //TODO: Implement geocoding process
        this.latitude = 0;
        this.longitude = 0;
    }
}
