package com.io.app.service;

import com.io.app.domain.KontoBankowe;
import org.springframework.transaction.annotation.Transactional;

import com.io.app.domain.User;
import com.io.app.repository.KontoBankoweRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.mockito.*;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.auditing.DateTimeProvider;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PrzelewyServiceIntTest {
    @Autowired
    private KontoBankoweService kontoBankoweService;

    private PrzelewyService przelewyService;

    @Autowired
    private AuditingHandler auditingHandler;

    @Autowired
    private KontoBankoweRepository kontoBankoweRepository;

    private UserService userService;

    private User user;
    private User user1;

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

        when(dateTimeProvider.getNow()).thenReturn(Optional.of(LocalDateTime.now()));
        auditingHandler.setDateTimeProvider(dateTimeProvider);
    }

    @Test
    @Transactional
    public void czyWysyłanyJestPrzelew() {
        KontoBankowe konto=kontoBankoweService.getKontoByUserId(user.getId());
        KontoBankowe konto1=kontoBankoweService.getKontoByUserId(user1.getId());
        przelewyService.createPrzelew(konto,konto1,1000.0);
        assertThat(konto.getStanKonta()).isEqualTo(4000);
        assertThat(konto1.getStanKonta()).isEqualTo(6000);
    }

}
