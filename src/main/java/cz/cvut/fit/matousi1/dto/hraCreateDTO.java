package cz.cvut.fit.matousi1.dto;


import java.sql.Timestamp;
import java.util.List;

public class hraCreateDTO {

    private final String nazev;
    private final String hardware;
    private final Timestamp datum_vydani;
    private final List<Integer> software_ids;

    public hraCreateDTO(String nazev, String hardware, Timestamp datum_vydani, List<Integer> software_ids) {
        this.nazev = nazev;
        this.hardware = hardware;
        this.datum_vydani = datum_vydani;
        this.software_ids = software_ids;
    }

    public Timestamp getDatum_vydani() {
        return datum_vydani;
    }

    public String getNazev() {
        return nazev;
    }

    public String getHardware() {
        return hardware;
    }

    public List<Integer> getSoftware_ids() { return software_ids; } 
}
