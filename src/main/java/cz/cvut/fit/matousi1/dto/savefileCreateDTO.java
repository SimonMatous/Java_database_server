package cz.cvut.fit.matousi1.dto;


import java.sql.Timestamp;

public class savefileCreateDTO {

    private final String name;
    private final Timestamp saved_at;
    private final int percOfGameFinished;
    private final int hra_id;

    public savefileCreateDTO(String name, Timestamp saved_at, int percOfGameFinished, int hra_id) {
        this.name = name;
        this.saved_at = saved_at;
        this.percOfGameFinished = percOfGameFinished;
        this.hra_id = hra_id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getSaved_at() {
        return saved_at;
    }

    public int getPercOfGameFinished() {
        return percOfGameFinished;
    }

    public int getHra_id() { return hra_id; }
}
