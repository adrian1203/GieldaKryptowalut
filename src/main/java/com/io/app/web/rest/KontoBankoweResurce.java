package com.io.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.KontoBankowe;
import com.io.app.service.KontoBankoweService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KontoBankoweResurce {

    private final KontoBankoweService kontoBankoweService;

    public KontoBankoweResurce(KontoBankoweService kontoBankoweService) {
        this.kontoBankoweService = kontoBankoweService;
    }

    @GetMapping("/konto-user")
    @Timed
    public KontoBankowe getKontoBankowe(){
        return this.kontoBankoweService.getKonto();
    }
}
