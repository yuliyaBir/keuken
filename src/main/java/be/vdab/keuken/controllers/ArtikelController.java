package be.vdab.keuken.controllers;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.domain.Artikelgroep;
import be.vdab.keuken.dto.ArtikelBeknopt;
import be.vdab.keuken.dto.NieuweFoodArtikel;
import be.vdab.keuken.dto.NieuweNonFoodArtikel;
import be.vdab.keuken.exceptions.ArtikelNietGevondenException;
import be.vdab.keuken.services.ArtikelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping("artikels")
public class ArtikelController {
    private final ArtikelService artikelService;

    public ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }
    private record ArtikelBeknoptMetArtikelgroep(long id, String naam, BigDecimal verkoopprijs, String artikelgroepNaam){
        private ArtikelBeknoptMetArtikelgroep(Artikel artikel) {
            this(artikel.getId(), artikel.getNaam(), artikel.getVerkoopprijs(), artikel.getArtikelgroep().getNaam());
        }
    }
    @GetMapping("{id}")
    ArtikelBeknopt findById(@PathVariable long id){
        return artikelService.findById(id).map(artikel -> new ArtikelBeknopt(artikel))
                .orElseThrow(ArtikelNietGevondenException::new);
    }
    @PostMapping("food")
    long createFoodArtikel(@RequestBody @Valid NieuweFoodArtikel nieuweArtikel){
        return artikelService.createFoodArtikel(nieuweArtikel);
    }
    @PostMapping("nonfood")
    long createNonFoodArtikel(@RequestBody @Valid NieuweNonFoodArtikel nieuweArtikel){
        return artikelService.createNonFoodArtikel(nieuweArtikel);
    }

    @GetMapping(params = "naamBevat")
    Stream<ArtikelBeknoptMetArtikelgroep> findByNaamBevat (String naamBevat){
        return artikelService.findByNaamBevat(naamBevat)
                .stream().map(artikel -> new ArtikelBeknoptMetArtikelgroep(artikel));
    }
    @GetMapping(params = "minimumWinst")
    Stream<ArtikelBeknopt> findMetMinimumWinst(BigDecimal minimumWinst){
        return artikelService.findMetMinimumWinst(minimumWinst)
                .stream().map(artikel -> new ArtikelBeknopt(artikel));
    }
    @GetMapping("verkoopprijzen/goedkoopste")
    BigDecimal findGoedkoopsteVerkoopprijs(){
        return artikelService.findGoedkoopsteVerkoopprijs();
    }
    private record GewijzigdeVerkoopprijs(@NotNull @Positive BigDecimal verkoopprijs){}
    @PatchMapping("{id}/verkoopprijs")
    void wijzigVerkoopprijs(@PathVariable long id,
                            @RequestBody @Valid GewijzigdeVerkoopprijs gewijzigdeVerkoopprijs){
        artikelService.wijzigVerkoopprijs(id, gewijzigdeVerkoopprijs.verkoopprijs);
    }
    @GetMapping("{id}/artikelGroepNaam")
    String findArtikelgroepNaamByArtikelId(@PathVariable long id){
        return artikelService.findById(id)
                .map(artikel -> artikel.getArtikelgroep().getNaam())
                .orElseThrow(ArtikelNietGevondenException::new);
    }
}
