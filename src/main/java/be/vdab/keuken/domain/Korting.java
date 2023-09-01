package be.vdab.keuken.domain;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Korting {
    private int vanafAantal;
    private BigDecimal percentage;

    public int getVanafAantal() {
        return vanafAantal;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korting korting = (Korting) o;
        return vanafAantal == korting.vanafAantal && Objects.equals(percentage, korting.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vanafAantal, percentage);
    }
}
