package be.vdab.keuken.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtikelgroepNietGevondenException extends RuntimeException{
    public ArtikelgroepNietGevondenException() {
        super("Artikelgroep niet gevonden.");
    }
}
