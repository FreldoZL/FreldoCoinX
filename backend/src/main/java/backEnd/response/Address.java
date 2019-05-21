package backEnd.response;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Address")
public class Address {

    @ApiObjectField(name = "ip")
    private String ip;

    @ApiObjectField(name = "type")
    private String type;

    @ApiObjectField(name = "continent_code")
    private String continent_code;

    @ApiObjectField(name = "continent_name")
    private String continent_name;

    @ApiObjectField(name = "country_code")
    private String country_code;

    @ApiObjectField(name = "country_name")
    private String country_name;

    @ApiObjectField(name = "region_code")
    private String region_code;

    @ApiObjectField(name = "region_name")
    private String region_name;

    @ApiObjectField(name = "city")
    private String city;

    @ApiObjectField(name = "zip")
    private String zip;

    @ApiObjectField(name = "latitude")
    private Double latitude;

    @ApiObjectField(name = "longitude")
    private Double longitude;

    @ApiObjectField(name = "location")
    private Location location;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContinent_code() {
        return continent_code;
    }

    public void setContinent_code(String continent_code) {
        this.continent_code = continent_code;
    }

    public String getContinent_name() {
        return continent_name;
    }

    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
