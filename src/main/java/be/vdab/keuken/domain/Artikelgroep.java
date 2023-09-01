package be.vdab.keuken.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "artikelgroepen")
public class Artikelgroep {
    @Id
    @GeneratedValue
    private long id;
    private String naam;

    public Artikelgroep(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }
    public Artikelgroep(){}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

}
