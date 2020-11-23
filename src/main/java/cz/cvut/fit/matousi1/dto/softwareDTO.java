package cz.cvut.fit.matousi1.dto;

import java.sql.Timestamp;


public class softwareDTO {

    private final String software_nazev;

    private final Timestamp datum_vyniku;

    public softwareDTO(String software_nazev, Timestamp datum_vyniku) {
        this.software_nazev = software_nazev;
        this.datum_vyniku = datum_vyniku;
    }

    public Timestamp getDatum_vyniku() {
        return datum_vyniku;
    }

    public String getSoftware_nazev() {
        return software_nazev;
    }

}
