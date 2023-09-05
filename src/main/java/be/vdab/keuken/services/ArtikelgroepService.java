package be.vdab.keuken.services;

import be.vdab.keuken.domain.Artikelgroep;
import be.vdab.keuken.repositories.ArtikelgroepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtikelgroepService {
    private final ArtikelgroepRepository artikelgroepRepository;

    public ArtikelgroepService(ArtikelgroepRepository artikelgroepRepository) {
        this.artikelgroepRepository = artikelgroepRepository;
    }
    public Optional<Artikelgroep> findById(long id){
        return artikelgroepRepository.findById(id);
    }
}
