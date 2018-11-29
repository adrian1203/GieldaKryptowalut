package com.io.app.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.OfertaSprzedazy;
import com.io.app.service.OfertaSprzedarzyService;
import com.io.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sprzedarz")
public class OfertaSprzedazyResource {

    private final OfertaSprzedarzyService ofertaSprzedarzyService;

    public OfertaSprzedazyResource(OfertaSprzedarzyService ofertaSprzedarzyService) {
        this.ofertaSprzedarzyService = ofertaSprzedarzyService;
    }

    @PostMapping("/save")
    @Timed
    public ResponseEntity<OfertaSprzedazy> createOfertaSprzedarzy(@RequestParam OfertaSprzedazy ofertaSprzedazy) {

        return ResponseEntity.ok(this.ofertaSprzedarzyService.createOfertaSprzedarzy(ofertaSprzedazy));

    }
    @GetMapping("findAll")
    public List<OfertaSprzedazy> findAllOfertaSprzedazy(){
       return  this.ofertaSprzedarzyService.findAllOfertaSprzedazy();
    }
}
