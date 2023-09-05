package be.vdab.keuken.domain;

import be.vdab.keuken.exceptions.ArtikelgroepHeeftAlDezeArtikelException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artikelgroepen")
public class Artikelgroep {
    @Id
    @GeneratedValue
    private long id;
    private String naam;
    @OneToMany (mappedBy = "artikelgroep")
    @OrderBy ("naam")
    private Set<Artikel> artikels;

    public Artikelgroep(long id, String naam) {
        this.id = id;
        this.naam = naam;
        artikels = new LinkedHashSet<Artikel>();
    }

    public Artikelgroep(String naam) {
        this.naam = naam;
        artikels = new LinkedHashSet<Artikel>();
    }

    public Artikelgroep(){}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
    public Set<Artikel> getArtikels() {
        return Collections.unmodifiableSet(artikels);
    }
    public void voegArtikelToe(Artikel artikel){
        if (!artikels.add(artikel)){
            throw new ArtikelgroepHeeftAlDezeArtikelException();
        }
    }

}

