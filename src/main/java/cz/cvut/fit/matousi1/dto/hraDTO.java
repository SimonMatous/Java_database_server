package cz.cvut.fit.matousi1.dto;


import java.sql.Timestamp;
import java.util.List;

public class hraDTO {

    private final String nazev;
    private final String hardware;
    private final Timestamp datum_vydani;
    private final List<String> softwares_nazvy;

    public hraDTO(String nazev, String hardware, Timestamp datum_vydani, List<String> softwares_nazvy) {
        this.nazev = nazev;
        this.hardware = hardware;
        this.datum_vydani = datum_vydani;
        this.softwares_nazvy = softwares_nazvy;
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
    public List<String> getSoftwares_nazvy() { return softwares_nazvy; }
}
