package be.vdab.keuken.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArtikelgroepHeeftAlDezeArtikelException extends RuntimeException{
    public ArtikelgroepHeeftAlDezeArtikelException() {
        super("Artikelgroep heeft al deze artikel.");
    }
}
