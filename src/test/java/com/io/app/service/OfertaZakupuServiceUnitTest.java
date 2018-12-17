package com.io.app.service;

import org.junit.Test;

import com.io.app.domain.OfertaZakupu;
import static org.assertj.core.api.Assertions.assertThat;

public class OfertaZakupuServiceUnitTest {

    private OfertaZakupuService ofertaZakupuService;


    @Test
    public void czyTworzonaJestOferta() {
        OfertaZakupu oferta = new OfertaZakupu();
        ofertaZakupuService.createOfertaZakupu(oferta);
        assertThat(!ofertaZakupuService.findAllOfertaZakupu().isEmpty());
    }

    @Test
    public void czyWyszukiwanaJestOferta() {
        OfertaZakupu oferta = new OfertaZakupu();
        ofertaZakupuService.createOfertaZakupu(oferta);
        OfertaZakupu of=ofertaZakupuService.findById(oferta.getId());
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
        OfertaZakupu oferta = new OfertaZakupu();
        ofertaZakupuService.createOfertaZakupu(oferta);
        Long id = oferta.getId();
        oferta.setId(id + 1);
        ofertaZakupuService.update(oferta);
        OfertaZakupu of=ofertaZakupuService.findById(id+1);
        assertThat(of.getId()).isEqualTo(oferta.getId());
        assertThat(of.getPozostalaIlosc()).isEqualTo(oferta.getPozostalaIlosc());
        assertThat(of.getCena()).isEqualTo(oferta.getCena());
        assertThat(of.getIlosc()).isEqualTo(oferta.getIlosc());
        assertThat(of.getDataWystawienia()).isEqualTo(oferta.getDataWystawienia());
        assertThat(of.getWaluta()).isEqualTo(oferta.getWaluta());
        assertThat(of.getTypZlecenia()).isEqualTo(oferta.getTypZlecenia());
    }
}
