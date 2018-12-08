package com.io.app.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.OfertaSprzedazy;
import com.io.app.service.OfertaSprzedazyService;
import com.io.app.web.rest.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprzedaz")
public class OfertaSprzedazyResource {

    private final OfertaSprzedazyService ofertaSprzedazyService;

    public OfertaSprzedazyResource(OfertaSprzedazyService ofertaSprzedazyService) {
        this.ofertaSprzedazyService = ofertaSprzedazyService;
    }

    @PostMapping("/save")
    @Timed
    public ResponseEntity<OfertaSprzedazy> createOfertaSprzedazy(@RequestBody OfertaSprzedazy ofertaSprzedazy) {

        return ResponseEntity.ok(this.ofertaSprzedazyService.createOfertaSprzedarzy(ofertaSprzedazy));

    }
    @GetMapping("findAll")
    public List<OfertaSprzedazy> findAllOfertaSprzedazy(){
       return  this.ofertaSprzedazyService.findAllOfertaSprzedazy();
    }
    @GetMapping("/oferty-sprzedazys")
    @Timed
    public ResponseEntity<List<OfertaSprzedazy>> getAllActual(Pageable pageable) {
        final Page<OfertaSprzedazy> page = ofertaSprzedazyService.getActualOfertSprzedazy(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/oferty-sprzedazys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    @GetMapping("/user-oferty-sprzedazys")
    @Timed
    public ResponseEntity<List<OfertaSprzedazy>> getAllForUser(Pageable pageable) {
        final Page<OfertaSprzedazy> page = ofertaSprzedazyService.getForUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-ferty-sprzedazys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
