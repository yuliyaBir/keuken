package be.vdab.keuken.dto;

import be.vdab.keuken.domain.Artikelgroep;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record NieuweFoodArtikel (@NotBlank String naam,
                                 @NotNull @PositiveOrZero BigDecimal aankoopprijs,
                                 @NotNull @PositiveOrZero BigDecimal verkoopprijs,
                                 @PositiveOrZero int houdbaarheid,
                                 @Positive long artikelgroepId){
}
