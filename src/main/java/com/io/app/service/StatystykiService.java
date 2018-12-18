package com.io.app.service;


import com.io.app.domain.OfertaSprzedazy;
import com.io.app.domain.OfertaZakupu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatystykiService {

    private final OfertaSprzedazyService ofertaSprzedazyService;
    private final OfertaZakupuService ofertaZakupuService;

    public StatystykiService(OfertaSprzedazyService ofertaSprzedazyService, OfertaZakupuService ofertaZakupuService) {
        this.ofertaSprzedazyService = ofertaSprzedazyService;
        this.ofertaZakupuService = ofertaZakupuService;
    }

    public Double getKursKupno() {
        double wartosc = 0.0;
        double ilosc = 0;
        List<OfertaSprzedazy> ofertaSprzedazies = this.ofertaSprzedazyService.getAllOfertySprzedazy();
        for (OfertaSprzedazy ofertaSprzedazy : ofertaSprzedazies) {
            wartosc += ofertaSprzedazy.getCena() * ofertaSprzedazy.getPozostalaIlosc();
            ilosc += ofertaSprzedazy.getPozostalaIlosc();
        }
        return wartosc / ilosc;


    }

    public Double getKursSprzedazy() {
        double wartosc = 0.0;
        double ilosc = 0;
        List<OfertaZakupu> ofertaZakupus = this.ofertaZakupuService.getAll();
        for (OfertaZakupu ofertaZakupu : ofertaZakupus) {
            wartosc += ofertaZakupu.getCena() * ofertaZakupu.getPozostalaIlosc();
            ilosc += ofertaZakupu.getPozostalaIlosc();
        }
        return wartosc / ilosc;


    }
}
