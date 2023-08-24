package be.vdab.keuken.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record NieuweArtikel(@NotBlank String naam, @NotNull @Positive BigDecimal aankoopprijs, @NotNull @Positive BigDecimal verkoopprijs) {
}
