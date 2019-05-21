package backEnd.response;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.List;

@ApiObject(name = "Location")
public class Location {

    @ApiObjectField(name = "geoname_id")
    private Integer geoname_id;

    @ApiObjectField(name = "capital")
    private String capital;

    @ApiObjectField(name = "languages")
    private List<Language> languages;

    @ApiObjectField(name = "country_flag")
    private String country_flag;

    @ApiObjectField(name = "country_flag_emoji")
    private String country_flag_emoji;

    @ApiObjectField(name = "country_flag_emoji_unicode")
    private String country_flag_emoji_unicode;

    @ApiObjectField(name = "calling_code")
    private String calling_code;

    @ApiObjectField(name = "is_eu")
    private String is_eu;

    public Integer getGeoname_id() {
        return geoname_id;
    }

    public void setGeoname_id(Integer geoname_id) {
        this.geoname_id = geoname_id;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getCountry_flag() {
        return country_flag;
    }

    public void setCountry_flag(String country_flag) {
        this.country_flag = country_flag;
    }

    public String getCountry_flag_emoji() {
        return country_flag_emoji;
    }

    public void setCountry_flag_emoji(String country_flag_emoji) {
        this.country_flag_emoji = country_flag_emoji;
    }

    public String getCountry_flag_emoji_unicode() {
        return country_flag_emoji_unicode;
    }

    public void setCountry_flag_emoji_unicode(String country_flag_emoji_unicode) {
        this.country_flag_emoji_unicode = country_flag_emoji_unicode;
    }

    public String getCalling_code() {
        return calling_code;
    }

    public void setCalling_code(String calling_code) {
        this.calling_code = calling_code;
    }

    public String getIs_eu() {
        return is_eu;
    }

    public void setIs_eu(String is_eu) {
        this.is_eu = is_eu;
    }
}