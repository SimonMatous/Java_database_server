package cz.cvut.fit.matousi1.entities;


import com.sun.istack.NotNull;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class savefile {

    @Id
    @SequenceGenerator(name="SequenceThree",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceThree")
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Timestamp saved_at;

    @NotNull
    private int PercOfGameFinished;

    @ManyToOne
    @NotNull /** CAN I ADD NOT NULL LIKE THIS ? */
    @JoinColumn( name = "hra_id")
    private hra Hra;

    public savefile() {}

    public savefile(String name, Timestamp saved_at, int percOfGameFinished, hra hra) {
        this.name = name;
        this.saved_at = saved_at;
        this.PercOfGameFinished = percOfGameFinished;
        this.Hra = hra;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getSaved_at() {
        return saved_at;
    }

    public void setSaved_at(Timestamp saved_at) {
        this.saved_at = saved_at;
    }

    public int getPercOfGameFinished() {
        return PercOfGameFinished;
    }

    public void setPercOfGameFinished(int percOfGameFinished) {
        PercOfGameFinished = percOfGameFinished;
    }

    public hra getHra() {
        return Hra;
    }

    public void setHra(hra hra) {
        Hra = hra;
    }
}
