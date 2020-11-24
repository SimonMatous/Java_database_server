package cz.cvut.fit.matousi1.dto;


public class locationCreateDTO {

    private final String state;
    private final String town;
    private final String address;

    public locationCreateDTO(String state, String town, String address) {
        this.state = state;
        this.town = town;
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public String getTown() {
        return town;
    }

    public String getAddress() {
        return address;
    }
}
