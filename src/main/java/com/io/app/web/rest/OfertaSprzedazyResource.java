package com.io.app.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.OfertaSprzedazy;
import com.io.app.service.OfertaSprzedazyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprzedarz")
public class OfertaSprzedazyResource {

    private final OfertaSprzedazyService ofertaSprzedazyService;

    public OfertaSprzedazyResource(OfertaSprzedazyService ofertaSprzedazyService) {
        this.ofertaSprzedazyService = ofertaSprzedazyService;
    }

    @PostMapping("/save")
    @Timed
    public ResponseEntity<OfertaSprzedazy> createOfertaSprzedazy(@RequestParam OfertaSprzedazy ofertaSprzedazy) {

        return ResponseEntity.ok(this.ofertaSprzedazyService.createOfertaSprzedarzy(ofertaSprzedazy));

    }
    @GetMapping("findAll")
    public List<OfertaSprzedazy> findAllOfertaSprzedazy(){
       return  this.ofertaSprzedazyService.findAllOfertaSprzedazy();
    }
}
