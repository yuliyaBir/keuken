package be.vdab.keuken.repositories;

import be.vdab.keuken.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    List<Artikel> findByNaamContainingOrderByNaam(String woord);
}
