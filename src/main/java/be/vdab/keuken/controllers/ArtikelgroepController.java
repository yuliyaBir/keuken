package be.vdab.keuken.controllers;

import be.vdab.keuken.dto.ArtikelBeknopt;
import be.vdab.keuken.exceptions.ArtikelgroepNietGevondenException;
import be.vdab.keuken.services.ArtikelgroepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("artikelGroepen")
public class ArtikelgroepController {
    private final ArtikelgroepService artikelgroepService;

    public ArtikelgroepController(ArtikelgroepService artikelgroepService) {
        this.artikelgroepService = artikelgroepService;
    }

    @GetMapping("{id}/artikels")
    Stream<ArtikelBeknopt> findArtikelsVan(@PathVariable long id){
        return artikelgroepService.findById(id)
                .orElseThrow(ArtikelgroepNietGevondenException::new)
                .getArtikels().stream().map(artikel -> new ArtikelBeknopt(artikel));
    }
}
