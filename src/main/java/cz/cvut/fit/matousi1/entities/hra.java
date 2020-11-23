package cz.cvut.fit.matousi1.entities;


import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

@Entity

public class hra {

    @Id
    private String nazev;

    @NotNull
    private String hardware;

    @NotNull
    private Timestamp datum_vydani;

    @ManyToMany
    @JoinTable(
            name = "hra_software",
            joinColumns = @JoinColumn(name = "hra_nazev"),
            inverseJoinColumns = @JoinColumn ( name = "software_nazev")
    )
    private List<software> softwares;

    public hra() {
    }

    public hra(String nazev, String hardware, Timestamp datum_vydani, List<software> softwares) {
        this.nazev = nazev;
        this.hardware = hardware;
        this.datum_vydani = datum_vydani;
        this.softwares = softwares;
    }

    public Timestamp getDatum_vydani() {
        return datum_vydani;
    }

    public void setDatum_vydani(Timestamp datum_vydani) {
        this.datum_vydani = datum_vydani;
    }

    public String getNazev() {
        return nazev;
    }

    public String getHardware() {
        return hardware;
    }

    public List<software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<software> softwares) {
        this.softwares = softwares;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }
}