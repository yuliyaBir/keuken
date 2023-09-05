package be.vdab.keuken.dto;

import be.vdab.keuken.domain.Artikel;

import java.math.BigDecimal;

public record ArtikelBeknopt(long id, String naam, BigDecimal verkoopprijs) {
    public ArtikelBeknopt(Artikel artikel) {
        this(artikel.getId(), artikel.getNaam(), artikel.getVerkoopprijs());
    }
}
