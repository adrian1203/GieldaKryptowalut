package com.io.app.service;

import com.io.app.domain.KontoBankowe;
import com.io.app.domain.User;
import com.io.app.repository.KontoBankoweRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.mockito.*;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.transaction.annotation.Transactional;

import com.io.app.GieldaKryptowalutApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GieldaKryptowalutApp.class)

public class KontoBankoweServiceUnitTest {
    @Autowired
    private KontoBankoweService kontoBankoweService;

    @Autowired
    private AuditingHandler auditingHandler;

    @Autowired
    private KontoBankoweRepository kontoBankoweRepository;

    private UserService userService;

    private User user;

    @Mock
    DateTimeProvider dateTimeProvider;

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

        when(dateTimeProvider.getNow()).thenReturn(Optional.of(LocalDateTime.now()));
        auditingHandler.setDateTimeProvider(dateTimeProvider);
    }

    @Test
    @Transactional
    public void czyTworzoneJestKonto() {
        kontoBankoweService.createNewKonto(user);
        KontoBankowe konto = kontoBankoweService.getKonto();
        assertThat(konto.getStanKonta()).isEqualTo(5000);
        assertThat(konto.getWaluta()).isEqualTo("PLN");
        assertThat(konto.getNumer()).isEqualTo("33333333333333");
        assertThat(konto.getUser()).isInstanceOf(User.class);
    }

    @Test
    @Transactional
    public void czyWyszukiwaneJestKonto() {
        KontoBankowe konto = kontoBankoweService.getKontoByUserId(user.getId());
        assertThat(konto.getStanKonta()).isEqualTo(5000);
        assertThat(konto.getWaluta()).isEqualTo("PLN");
        assertThat(konto.getNumer()).isEqualTo("33333333333333");
        assertThat(konto.getUser()).isInstanceOf(User.class);
    }

    public void czyZmienianaJestWartosc() {
        KontoBankowe konto = kontoBankoweService.getKontoByUserId(user.getId());
        konto=kontoBankoweService.updateStanKonta(konto,1111.0);
        assertThat(konto.getStanKonta()).isEqualTo(6111);
    }
    @Test
    @Transactional
    public void test(){
        assertThat(true);
    }

}
