package be.vdab.keuken.services;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.domain.FoodArtikel;
import be.vdab.keuken.domain.NonFoodArtikel;
import be.vdab.keuken.dto.NieuweFoodArtikel;
import be.vdab.keuken.dto.NieuweNonFoodArtikel;
import be.vdab.keuken.exceptions.ArtikelBestaatAlException;
import be.vdab.keuken.exceptions.ArtikelNietGevondenException;
import be.vdab.keuken.exceptions.ArtikelgroepInArtikelNietGevondenException;
import be.vdab.keuken.repositories.ArtikelRepository;
import be.vdab.keuken.repositories.ArtikelgroepRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtikelService {
    private final ArtikelRepository artikelRepository;
    private final ArtikelgroepRepository artikelgroepRepository;

    public ArtikelService(ArtikelRepository artikelRepository, ArtikelgroepRepository artikelgroepRepository) {
        this.artikelRepository = artikelRepository;
        this.artikelgroepRepository = artikelgroepRepository;
    }
    public Optional<Artikel> findById (long id){
        return artikelRepository.findById(id);
    }
    @Transactional
    public long createFoodArtikel(NieuweFoodArtikel nieuweFoodArtikel){
        try{
            var artikelgroep = artikelgroepRepository.findById(nieuweFoodArtikel.artikelgroepId())
                    .orElseThrow(ArtikelgroepInArtikelNietGevondenException::new);
            var artikel= new FoodArtikel(
                    nieuweFoodArtikel.naam(),
                    nieuweFoodArtikel.aankoopprijs(),
                    nieuweFoodArtikel.verkoopprijs(),
                    nieuweFoodArtikel.houdbaarheid(), artikelgroep);
            artikelRepository.save(artikel);
            return artikel.getId();
        }catch(DataIntegrityViolationException ex){
            throw new ArtikelBestaatAlException();
        }
    }
    @Transactional
    public long createNonFoodArtikel(NieuweNonFoodArtikel nieuweNonFoodArtikel){
        try{
            var artikelgroep = artikelgroepRepository.findById(nieuweNonFoodArtikel.artikelgroepId()).orElseThrow(ArtikelgroepInArtikelNietGevondenException::new);
            var artikel = new NonFoodArtikel(
                    nieuweNonFoodArtikel.naam(),
                    nieuweNonFoodArtikel.aankoopprijs(),
                    nieuweNonFoodArtikel.verkoopprijs(),
                    nieuweNonFoodArtikel.garantie(), artikelgroep);
            artikelRepository.save(artikel);
            return artikel.getId();
        }catch(DataIntegrityViolationException ex){
            throw new ArtikelBestaatAlException();
        }
    }
    public List<Artikel> findByNaamBevat(String woord){
        return artikelRepository.findByNaamContainingOrderByNaam(woord);
    }
    public List<Artikel> findMetMinimumWinst(BigDecimal winst){
        return artikelRepository.findMetMinimumWinst(winst);
    }
    public BigDecimal findGoedkoopsteVerkoopprijs(){
        return artikelRepository.findGoedkoopsteVerkoopprijs();
    }
    @Transactional
    public void wijzigVerkoopprijs(long id, BigDecimal verkoopprijs){
        artikelRepository.findById(id)
                .orElseThrow(ArtikelNietGevondenException::new)
                .setVerkoopprijs(verkoopprijs);
    }
}
