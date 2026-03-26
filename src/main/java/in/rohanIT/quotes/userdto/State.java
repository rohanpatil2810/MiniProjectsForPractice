package in.rohanIT.quotes.userdto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    private String name;
    private List<City> cities;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<City> getCities() { return cities; }
    public void setCities(List<City> cities) { this.cities = cities; }
}