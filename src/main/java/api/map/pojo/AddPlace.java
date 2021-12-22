package api.map.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class AddPlace {

    @JsonProperty("location")
    public Location Location;

    @JsonProperty("accuracy")
    public int Accuracy;

    @JsonProperty("name")
    public String Name;

    @JsonProperty("phone_number")
    public String PhoneNumber;

    @JsonProperty("address")
    public String Address;

    @JsonProperty("types")
    public List<String> Types;

    @JsonProperty("website")
    public String Website;

    @JsonProperty("language")
    public String Language;

    public AddPlace() {
    }

    public Location getLocation() {
        return Location;
    }

    public void setLocation(Location location) {
        Location = location;
    }

    public int getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(int accuracy) {
        Accuracy = accuracy;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<String> getTypes() {
        System.out.println(Types);
        return Types;
    }

    public void setTypes(List<String> types) {
        Types = types;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
