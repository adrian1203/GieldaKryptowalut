package com.io.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.KontoBankowe;
import com.io.app.service.CryptocService;
import com.io.app.service.KontoBankoweService;
import com.io.app.service.UserService;
import org.bitcoinj.params.RegTestParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KontoBankoweResurce {

    private final KontoBankoweService kontoBankoweService;
    private final CryptocService cryptocService;
    private final UserService userService;

    public KontoBankoweResurce(KontoBankoweService kontoBankoweService, CryptocService cryptocService, UserService userService) {
        this.kontoBankoweService = kontoBankoweService;
        this.cryptocService = cryptocService;
        this.userService = userService;
    }

    @GetMapping("/konto-user")
    @Timed
    public KontoBankowe getKontoBankowe(){
        return this.kontoBankoweService.getKonto();
    }

    @GetMapping("/kontoBTC-user")
    @Timed
    public Long getWalletBalance(){
        return this.cryptocService.GetWalletBalance(this.userService.getUserWithAuthorities().get().getLogin(), RegTestParams.get());
    }
}
