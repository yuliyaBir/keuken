package be.vdab.keuken.domain;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class ArtikelTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Test
    void jeKanMeerdereArtikelsToevoegenAanEenArtikelgroep(){
        var artikelgroep = new Artikelgroep("test");
        var foodArtikel = new FoodArtikel("test1", BigDecimal.ZERO, BigDecimal.ONE, 4, artikelgroep);
        artikelgroep.voegArtikelToe(foodArtikel);
        var nonFoodArtikel = new NonFoodArtikel("test2", BigDecimal.ZERO, BigDecimal.ONE, 4, artikelgroep);
        artikelgroep.voegArtikelToe(nonFoodArtikel);
    }
    @Test
    void nadatJeEenArtikelOpslaatBehoortHijTotZijnArtikelgroep(){
        var artikelgroep = new Artikelgroep("test");
        var foodArtikel = new FoodArtikel("test1", BigDecimal.ZERO, BigDecimal.ONE, 4, artikelgroep);
        artikelgroep.voegArtikelToe(foodArtikel);
        ReflectionTestUtils.setField(foodArtikel, "id", 1);
        assertThat(artikelgroep.getArtikels().contains(foodArtikel)).isTrue();
    }
}