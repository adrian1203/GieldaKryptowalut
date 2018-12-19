package com.io.app.service;

import com.io.app.domain.KontoBankowe;
import org.springframework.transaction.annotation.Transactional;

import com.io.app.domain.User;
import org.apache.commons.lang3.RandomStringUtils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class PrzelewyServiceUnitTest {
    @Autowired
    private KontoBankoweService kontoBankoweService;

    private PrzelewyService przelewyService;

    private User user;
    private User user1;

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

        user1 = new User();
        user1.setLogin("john");
        user1.setPassword(RandomStringUtils.random(60));
        user1.setActivated(true);
        user1.setEmail("john@localhost");
        user1.setFirstName("john");
        user1.setLastName("doe");
        user1.setImageUrl("http://placehold.it/50x50");
        user1.setLangKey("en");

        kontoBankoweService.createNewKonto(user);
        kontoBankoweService.createNewKonto(user1);
    }

    @Test
    @Transactional
    public void czyWysylanyJestPrzelew() {
        double stan=0,stan1=0;
        KontoBankowe konto=kontoBankoweService.getKontoByUserId(user.getId());
        KontoBankowe konto1=kontoBankoweService.getKontoByUserId(user1.getId());
        przelewyService.createPrzelew(konto,konto1,1000.0);
        stan=konto.getStanKonta();
        stan1=konto1.getStanKonta();
        assertThat(konto.getStanKonta()).isEqualTo(stan-1000);
        assertThat(konto1.getStanKonta()).isEqualTo(stan1+1000);
    }

    @Test
    @Transactional
    public void czyWysylanyJestPrzelewNaToSamoKonto() {
        double stan=0,stan1=0;
        KontoBankowe konto=kontoBankoweService.getKontoByUserId(user.getId());
        stan=konto.getStanKonta();
        przelewyService.createPrzelew(konto,konto,1000.0);
        assertThat(konto.getStanKonta()).isEqualTo(stan);
    }

    @Test
    @Transactional
    public void czyWysylanyJestUjemnyPrzelew() {
        double stan=0,stan1=0;
        KontoBankowe konto=kontoBankoweService.getKontoByUserId(user.getId());
        KontoBankowe konto1=kontoBankoweService.getKontoByUserId(user1.getId());
        przelewyService.createPrzelew(konto,konto1,-1000.0);
        stan=konto.getStanKonta();
        stan1=konto1.getStanKonta();
        assertThat(konto.getStanKonta()).isEqualTo(stan);
        assertThat(konto1.getStanKonta()).isEqualTo(stan1);
    }
    @Test
    @Transactional
    public void czyWysylanyJestZaDuzyPrzelew() {
        double stan=0,stan1=0;
        KontoBankowe konto=kontoBankoweService.getKontoByUserId(user.getId());
        KontoBankowe konto1=kontoBankoweService.getKontoByUserId(user1.getId());
        stan=konto.getStanKonta();
        stan1=konto1.getStanKonta();
        przelewyService.createPrzelew(konto,konto1,stan+1000.0);
        assertThat(konto.getStanKonta()).isEqualTo(stan);
        assertThat(konto1.getStanKonta()).isEqualTo(stan1);
    }


}
