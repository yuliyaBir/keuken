package be.vdab.keuken.controllers;

import be.vdab.keuken.domain.Artikel;
import be.vdab.keuken.dto.NieuweArtikel;
import be.vdab.keuken.exceptions.ArtikelNietGevondenException;
import be.vdab.keuken.services.ArtikelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
    @PostMapping
    long create(@RequestBody @Valid NieuweArtikel nieuweArtikel){
        return artikelService.create(nieuweArtikel);
    }
    @GetMapping(params = "naamBevat")
    List<Artikel> findByNaamBevat (String naamBevat){
        return artikelService.findByNaamBevat(naamBevat);
    }
    @GetMapping(params = "minimumWinst")
    List<Artikel> findMetMinimumWinst(BigDecimal minimumWinst){
        return artikelService.findMetMinimumWinst(minimumWinst);
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
