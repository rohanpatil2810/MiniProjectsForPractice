package in.rohanIT.quotes.userdto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private String name;
    private List<State> states;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<State> getStates() { return states; }
    public void setStates(List<State> states) { this.states = states; }
}