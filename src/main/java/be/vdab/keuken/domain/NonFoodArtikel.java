package be.vdab.keuken.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel{
    private int garantie;

    public NonFoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int garantie, Artikelgroep artikelgroep) {
        super(naam, aankoopprijs, verkoopprijs, artikelgroep);
        this.garantie = garantie;
    }

    protected NonFoodArtikel() {}

    public int getGarantie() {
        return garantie;
    }
}
