package be.vdab.keuken.controllers;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.dto.NieuweFoodArtikel;
import be.vdab.keuken.dto.NieuweNonFoodArtikel;
import be.vdab.keuken.exceptions.ArtikelNietGevondenException;
import be.vdab.keuken.services.ArtikelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("artikels")
public class ArtikelController {
    private final ArtikelService artikelService;

    public ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }
    @GetMapping("{id}")
    Artikel findById(@PathVariable long id){
        return artikelService.findById(id)
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
    private record ArtikelBeknopt(long id, String naam, BigDecimal verkoopprijs){
        private ArtikelBeknopt (Artikel artikel){
            this(artikel.getId(), artikel.getNaam(), artikel.getVerkoopprijs());
        }
    }
    @GetMapping(params = "naamBevat")
    Stream<ArtikelBeknopt> findByNaamBevat (String naamBevat){
        return artikelService.findByNaamBevat(naamBevat)
                .stream().map(artikel -> new ArtikelBeknopt(artikel));
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

}
