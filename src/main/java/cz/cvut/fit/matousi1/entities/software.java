package cz.cvut.fit.matousi1.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class software {



    @Id
    @SequenceGenerator(name="SequenceTwo",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceTwo")
    private int id;

    @NotNull
    private String software_nazev;

    @NotNull
    private Timestamp datum_vyniku;

    public software() {
    }

    public software(String software_nazev, Timestamp datum_vyniku) {
        this.software_nazev = software_nazev;
        this.datum_vyniku = datum_vyniku;
    }

    public Timestamp getDatum_vyniku() {
        return datum_vyniku;
    }

    public void setDatum_vyniku(Timestamp datum_vyniku) {
        this.datum_vyniku = datum_vyniku;
    }

    public String getSoftware_nazev() {
        return software_nazev;
    }

    public int getId() {
        return id;
    }
    public void setSoftware_nazev(String software_nazev) {
        this.software_nazev = software_nazev;
    }
}
