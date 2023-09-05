package be.vdab.keuken.repositories;

import be.vdab.keuken.domain.Artikel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    @EntityGraph(attributePaths = "artikelgroep")
    List<Artikel> findByNaamContainingOrderByNaam(String woord);
    @Query("""
        select a from Artikel a where a.verkoopprijs >= a.aankoopprijs + :winst
        order by a.verkoopprijs
        """)
    List<Artikel> findMetMinimumWinst(BigDecimal winst);
    @Query("""
        select min(a.verkoopprijs)
        from Artikel a    
        """)
    BigDecimal findGoedkoopsteVerkoopprijs();
}
