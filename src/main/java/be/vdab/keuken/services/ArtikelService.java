package be.vdab.keuken.services;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.dto.NieuweArtikel;
import be.vdab.keuken.exceptions.ArtikelBestaatAlException;
import be.vdab.keuken.repositories.ArtikelRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtikelService {
    private final ArtikelRepository artikelRepository;

    public ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }
    public Optional<Artikel> findById (long id){
        return artikelRepository.findById(id);
    }
    @Transactional
    public long create(NieuweArtikel nieuweArtikel){
        try{
            var artikel= new Artikel(nieuweArtikel.naam(), nieuweArtikel.aankoopprijs(),nieuweArtikel.verkoopprijs());
            artikelRepository.save(artikel);
            return artikel.getId();
        }catch(DataIntegrityViolationException ex){
            throw new ArtikelBestaatAlException();
        }
    }
    public List<Artikel> findByNaamBevat(String woord){
        return artikelRepository.findByNaamContainingOrderByNaam(woord);
    }
}
