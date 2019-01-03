package com.io.app.service;


import com.io.app.domain.OfertaSprzedazy;
import com.io.app.domain.OfertaZakupu;
import com.io.app.repository.OfertaSprzedazyRepository;
import com.io.app.repository.OfertaZakupuRepository;
import com.io.app.repository.ZrealizowaneZleceniaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatystykiService {

    private final OfertaSprzedazyService ofertaSprzedazyService;
    private final OfertaZakupuService ofertaZakupuService;
    private final OfertaSprzedazyRepository ofertaSprzedazyRepository;
    private final OfertaZakupuRepository ofertaZakupuRepository;
    private final ZrealizowaneZleceniaRepository zrealizowaneZleceniaRepository;

    public StatystykiService(OfertaSprzedazyService ofertaSprzedazyService, OfertaZakupuService ofertaZakupuService, OfertaSprzedazyRepository ofertaSprzedazyRepository, OfertaZakupuRepository ofertaZakupuRepository, ZrealizowaneZleceniaRepository zrealizowaneZleceniaRepository) {
        this.ofertaSprzedazyService = ofertaSprzedazyService;
        this.ofertaZakupuService = ofertaZakupuService;
        this.ofertaSprzedazyRepository = ofertaSprzedazyRepository;
        this.ofertaZakupuRepository = ofertaZakupuRepository;
        this.zrealizowaneZleceniaRepository = zrealizowaneZleceniaRepository;
    }

    public Double getKursKupno() {
        double wartosc = 0.0;
        double ilosc = 0;
        List<OfertaSprzedazy> ofertaSprzedazies = this.ofertaSprzedazyService.getAllOfertySprzedazy();
        for (OfertaSprzedazy ofertaSprzedazy : ofertaSprzedazies) {
            if (ofertaSprzedazy.getCena() != 0) {
                wartosc += ofertaSprzedazy.getCena() * ofertaSprzedazy.getPozostalaIlosc();
                ilosc += ofertaSprzedazy.getPozostalaIlosc();
            }

        }
        return wartosc / ilosc;


    }

    public Double getKursSprzedazy() {
        double wartosc = 0.0;
        double ilosc = 0;
        List<OfertaZakupu> ofertaZakupus = this.ofertaZakupuService.getAll();
        for (OfertaZakupu ofertaZakupu : ofertaZakupus) {
            if (ofertaZakupu.getCena() != 0) {
                wartosc += ofertaZakupu.getCena() * ofertaZakupu.getPozostalaIlosc();
                ilosc += ofertaZakupu.getPozostalaIlosc();
            }
        }
        return wartosc / ilosc;


    }

    public List<Object> getOfertaSprzedazyChart(){
       return this.ofertaSprzedazyRepository.getOferaSprzedazyChart();
    }

    public List<Object> getOfertaZakupuChart(){
        return this.ofertaZakupuRepository.getOferaZakupuChart();
    }
    public List<Object> getZleceniaChart(){
        return this.zrealizowaneZleceniaRepository.getZleceniaChart();
    }
    public List<Object> getSumZleceniaChart(){
        return this.zrealizowaneZleceniaRepository.getSumZleceniaChart();
    }

}
