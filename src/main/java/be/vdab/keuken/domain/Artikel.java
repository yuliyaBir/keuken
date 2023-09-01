package be.vdab.keuken.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels")
@DiscriminatorColumn(name = "soort")
public abstract class Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal aankoopprijs;
    private BigDecimal verkoopprijs;
    @ElementCollection
    @CollectionTable(name = "kortingen",
        joinColumns = @JoinColumn(name = "artikelId"))
    @OrderBy("vanafAantal")
    private Set<Korting> kortingen;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "artikelgroepId")
    private Artikelgroep artikelgroep;


    public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, Artikelgroep artikelgroep) {
        this.naam = naam;
        this.aankoopprijs = aankoopprijs;
        this.verkoopprijs = verkoopprijs;
        this.artikelgroep = artikelgroep;
        kortingen = new LinkedHashSet<>();
    }

    protected Artikel() {
    }

    public void setVerkoopprijs(BigDecimal verkoopprijs) {
        this.verkoopprijs = verkoopprijs;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getAankoopprijs() {
        return aankoopprijs;
    }

    public BigDecimal getVerkoopprijs() {
        return verkoopprijs;
    }
    public Set<Korting> getKortingen(){
        return Collections.unmodifiableSet(kortingen);
    }

    public Artikelgroep getArtikelgroep() {
        return artikelgroep;
    }
}
