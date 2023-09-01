package be.vdab.keuken.repositories;

import be.vdab.keuken.domain.Artikel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
@DataJpaTest
@Sql({"/artikelgroepen.sql","/artikels.sql"})
class ArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final String ARTIKELS = "artikels";
    private final ArtikelRepository artikelRepository;

    public ArtikelRepositoryTest(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }
    private long idVanTest1Artikel(){
        return jdbcTemplate.queryForObject("select id from artikels where naam = 'test1'", Long.class);
    }
    @Test
    void findById() {
        assertThat(artikelRepository.findById(idVanTest1Artikel()).get().getNaam())
                .isEqualTo("test1");
    }

    @Test
    void findByOnbestaandeIdVindtGeenArtikel() {
        assertThat(artikelRepository.findById(Long.MAX_VALUE)).isEmpty();
    }
}