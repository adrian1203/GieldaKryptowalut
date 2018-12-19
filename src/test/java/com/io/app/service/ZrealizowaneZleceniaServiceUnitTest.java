package com.io.app.service;
import com.io.app.domain.*;
import com.io.app.repository.ZrealizowaneZleceniaRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import com.io.app.domain.OfertaZakupu;
import static org.assertj.core.api.Assertions.assertThat;


public class ZrealizowaneZleceniaServiceUnitTest {
    private ZrealizowaneZleceniaRepository zrealizowaneZleceniaRepository;
    private UserService userService;
    private OfertaZakupuService ofertaZakupuService;
    private OfertaSprzedazyService ofertaSprzedazyService;
    private CryptocService cryptocService;
    private KontoBankoweService kontoBankoweService;
    private PrzelewyService przelewyService;
    private ZrealizowaneZleceniaService zrealizowaneZleceniaService;
    private ZrealizowaneZlecenia zrealizowaneZlecenia;
    private User user;
    private KontoBankowe kontoBankowe;
    private OfertaSprzedazy ofertaSprzedazy;
    private OfertaZakupu ofertaZakupu;




    @Before
    public void init() {

        user = new User();
        user.setLogin("johndoe");
        user.setPassword(RandomStringUtils.random(60));
        user.setActivated(true);
        user.setEmail("johndoe@localhost");
        user.setFirstName("john");
        user.setLastName("doe");
        user.setImageUrl("http://placehold.it/50x50");
        user.setLangKey("en");
    }

    @Test
    public void czyTworzoneJestZlecenie() {
        zrealizowaneZlecenia = zrealizowaneZleceniaService.createZrealizowaneZlecenia(zrealizowaneZlecenia);
        assertThat(!zrealizowaneZleceniaService.findAllZrealizowaneZlecenia().isEmpty());
    }

    @Test
    public void sprawdzanieKontaOk() {
        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        kontoBankowe.setStanKonta(10000);
        assertThat(zrealizowaneZleceniaService.checkStanKonta(user,10L,10000D));
    }
    @Test
    public void sprawdzanieKontaNieOk() {
        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        kontoBankowe.setStanKonta(500);
        assertThat(!zrealizowaneZleceniaService.checkStanKonta(user,10L,10000D));
    }

    @Test
    public void czyTransferSieUdaje() {
        User userKupujacy = new User();
        KontoBankowe kontoBankoweKupujace = new KontoBankowe();
        double stan=0;

        kontoBankoweService.createNewKonto(userKupujacy);
        kontoBankoweKupujace=kontoBankoweService.getKontoByUserId(userKupujacy.getId());
        kontoBankoweKupujace.setStanKonta(100000);

        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        stan=kontoBankowe.getStanKonta();

        zrealizowaneZleceniaService.createKontoBankoweTransfer(user,userKupujacy,10L,10000D);
        assertThat(kontoBankowe.getStanKonta()==stan+100000);
        assertThat(kontoBankoweKupujace.getStanKonta()==0);
    }

    @Test
    public void czyTransferSieNieUdaje() {
        User userKupujacy = new User();
        KontoBankowe kontoBankoweKupujace = new KontoBankowe();
        double stan=0;

        kontoBankoweService.createNewKonto(userKupujacy);
        kontoBankoweKupujace=kontoBankoweService.getKontoByUserId(userKupujacy.getId());
        kontoBankoweKupujace.setStanKonta(1000);

        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        stan=kontoBankowe.getStanKonta();

        zrealizowaneZleceniaService.createKontoBankoweTransfer(user,userKupujacy,10L,10000D);
        assertThat(kontoBankowe.getStanKonta()==stan+100000);
        assertThat(kontoBankoweKupujace.getStanKonta()==0);
    }


    @Test
    public void checkOk() {
        User userKupujacy = new User();
        KontoBankowe kontoBankoweKupujace = new KontoBankowe();
        double stan=0;

        kontoBankoweService.createNewKonto(userKupujacy);
        kontoBankoweKupujace=kontoBankoweService.getKontoByUserId(userKupujacy.getId());
        kontoBankoweKupujace.setStanKonta(100000);

        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        stan=kontoBankowe.getStanKonta();

        ofertaSprzedazy.setCena(10000);
        ofertaSprzedazy.setId(user.getId());
        ofertaSprzedazy.setPozostalaIlosc(10L);
        ofertaZakupu.setCena(20000);
        ofertaZakupu.setId(userKupujacy.getId());
        ofertaZakupu.setPozostalaIlosc(20L);

        assertThat(zrealizowaneZleceniaService.check(ofertaSprzedazy,ofertaZakupu,true));
    }
    @Test
    public void checkPKCFalse() {
        User userKupujacy = new User();
        KontoBankowe kontoBankoweKupujace = new KontoBankowe();
        double stan=0;

        kontoBankoweService.createNewKonto(userKupujacy);
        kontoBankoweKupujace=kontoBankoweService.getKontoByUserId(userKupujacy.getId());
        kontoBankoweKupujace.setStanKonta(100000);

        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        stan=kontoBankowe.getStanKonta();

        ofertaSprzedazy.setCena(10000);
        ofertaSprzedazy.setId(user.getId());
        ofertaSprzedazy.setPozostalaIlosc(10L);
        ofertaZakupu.setCena(20000);
        ofertaZakupu.setId(userKupujacy.getId());
        ofertaZakupu.setPozostalaIlosc(20L);

        assertThat(!zrealizowaneZleceniaService.check(ofertaSprzedazy,ofertaZakupu,false));
    }

    @Test
    public void checkZlaIlosc() {
        User userKupujacy = new User();
        KontoBankowe kontoBankoweKupujace = new KontoBankowe();
        double stan=0;

        kontoBankoweService.createNewKonto(userKupujacy);
        kontoBankoweKupujace=kontoBankoweService.getKontoByUserId(userKupujacy.getId());
        kontoBankoweKupujace.setStanKonta(100000);

        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        stan=kontoBankowe.getStanKonta();

        ofertaSprzedazy.setCena(10000);
        ofertaSprzedazy.setId(user.getId());
        ofertaSprzedazy.setPozostalaIlosc(10L);
        ofertaZakupu.setCena(20000);
        ofertaZakupu.setId(userKupujacy.getId());
        ofertaZakupu.setPozostalaIlosc(8L);

        assertThat(!zrealizowaneZleceniaService.check(ofertaSprzedazy,ofertaZakupu,true));
    }

    @Test
    public void checkZlaCena() {
        User userKupujacy = new User();
        KontoBankowe kontoBankoweKupujace = new KontoBankowe();
        double stan=0;

        kontoBankoweService.createNewKonto(userKupujacy);
        kontoBankoweKupujace=kontoBankoweService.getKontoByUserId(userKupujacy.getId());
        kontoBankoweKupujace.setStanKonta(100000);

        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        stan=kontoBankowe.getStanKonta();

        ofertaSprzedazy.setCena(10000);
        ofertaSprzedazy.setId(user.getId());
        ofertaSprzedazy.setPozostalaIlosc(10L);
        ofertaZakupu.setCena(8000);
        ofertaZakupu.setId(userKupujacy.getId());
        ofertaZakupu.setPozostalaIlosc(20L);

        assertThat(!zrealizowaneZleceniaService.check(ofertaSprzedazy,ofertaZakupu,true));
    }

    @Test
    public void checkZlyUser() {
        User userKupujacy = new User();
        KontoBankowe kontoBankoweKupujace = new KontoBankowe();
        double stan=0;

        kontoBankoweService.createNewKonto(userKupujacy);
        kontoBankoweKupujace=kontoBankoweService.getKontoByUserId(userKupujacy.getId());
        kontoBankoweKupujace.setStanKonta(100000);

        kontoBankoweService.createNewKonto(user);
        kontoBankowe=kontoBankoweService.getKontoByUserId(user.getId());
        stan=kontoBankowe.getStanKonta();

        ofertaSprzedazy.setCena(10000);
        ofertaSprzedazy.setId(user.getId());
        ofertaSprzedazy.setPozostalaIlosc(10L);
        ofertaZakupu.setCena(8000);
        ofertaZakupu.setId(user.getId());
        ofertaZakupu.setPozostalaIlosc(20L);

        assertThat(!zrealizowaneZleceniaService.check(ofertaSprzedazy,ofertaZakupu,true));
    }
}
