package com.io.app.service;

import org.junit.Test;

import com.io.app.domain.OfertaSprzedazy;
import static org.assertj.core.api.Assertions.assertThat;

public class OfertaSprzedazyServiceUnitTest {

    private  OfertaSprzedazyService ofertaSprzedazyService;



    @Test
    public void czyTworzonaJestOferta() {
        OfertaSprzedazy oferta = new OfertaSprzedazy();
        ofertaSprzedazyService.createOfertaSprzedarzy(oferta);
        assertThat(!ofertaSprzedazyService.findAllOfertaSprzedazy().isEmpty());
    }
    @Test
    public void czyWyszukiwanaJestOferta() {
        OfertaSprzedazy oferta = new OfertaSprzedazy();
        ofertaSprzedazyService.createOfertaSprzedarzy(oferta);
        OfertaSprzedazy of=ofertaSprzedazyService.findById(oferta.getId());
        assertThat(of.getId()).isEqualTo(oferta.getId());
        assertThat(of.getPozostalaIlosc()).isEqualTo(oferta.getPozostalaIlosc());
        assertThat(of.getCena()).isEqualTo(oferta.getCena());
        assertThat(of.getIlosc()).isEqualTo(oferta.getIlosc());
        assertThat(of.getDataWystawienia()).isEqualTo(oferta.getDataWystawienia());
        assertThat(of.getWaluta()).isEqualTo(oferta.getWaluta());
        assertThat(of.getTypZlecenia()).isEqualTo(oferta.getTypZlecenia());
    }

    @Test
    public void czyZmienianaJestOferta() {
        OfertaSprzedazy oferta = new OfertaSprzedazy();
        ofertaSprzedazyService.createOfertaSprzedarzy(oferta);
        Long id = oferta.getId();
        oferta.setId(id + 1);
        ofertaSprzedazyService.update(oferta);
        OfertaSprzedazy of=ofertaSprzedazyService.findById(id+1);
        assertThat(of.getId()).isEqualTo(oferta.getId());
        assertThat(of.getPozostalaIlosc()).isEqualTo(oferta.getPozostalaIlosc());
        assertThat(of.getCena()).isEqualTo(oferta.getCena());
        assertThat(of.getIlosc()).isEqualTo(oferta.getIlosc());
        assertThat(of.getDataWystawienia()).isEqualTo(oferta.getDataWystawienia());
        assertThat(of.getWaluta()).isEqualTo(oferta.getWaluta());
        assertThat(of.getTypZlecenia()).isEqualTo(oferta.getTypZlecenia());
    }

    @Test
    public void czyZmienianaJestOfertaV2() {
        OfertaSprzedazy oferta = new OfertaSprzedazy();
        ofertaSprzedazyService.createOfertaSprzedarzy(oferta);
        Long id = oferta.getId();
        oferta.setPozostalaIlosc(20L);
        oferta.setCena(2000);
        ofertaSprzedazyService.update(oferta);
        OfertaSprzedazy of=ofertaSprzedazyService.findById(id);
        assertThat(of.getId()).isEqualTo(oferta.getId());
        assertThat(of.getPozostalaIlosc()).isEqualTo(20L);
        assertThat(of.getCena()).isEqualTo(2000);
        assertThat(of.getIlosc()).isEqualTo(oferta.getIlosc());
        assertThat(of.getDataWystawienia()).isEqualTo(oferta.getDataWystawienia());
        assertThat(of.getWaluta()).isEqualTo(oferta.getWaluta());
        assertThat(of.getTypZlecenia()).isEqualTo(oferta.getTypZlecenia());
    }
}
