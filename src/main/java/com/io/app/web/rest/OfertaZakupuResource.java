package com.io.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.OfertaSprzedazy;
import com.io.app.domain.OfertaZakupu;
import com.io.app.service.OfertaZakupuService;
import com.io.app.web.rest.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/zakup")
public class OfertaZakupuResource {

    private final OfertaZakupuService ofertaZakupuService;

    public OfertaZakupuResource(OfertaZakupuService ofertaZakupuService) {
        this.ofertaZakupuService = ofertaZakupuService;
    }
    @PostMapping("/save")
    @Timed
    public ResponseEntity<OfertaZakupu> createOfertaZakupu(@RequestBody  OfertaZakupu ofertaZakupu) {

        return ResponseEntity.ok(this.ofertaZakupuService.createOfertaZakupu(ofertaZakupu));

    }
    @GetMapping("findAll")
    public List<OfertaZakupu> findAllOfertaZakupu(){
        return  this.ofertaZakupuService.findAllOfertaZakupu();
    }

    @GetMapping("/oferty-zakupus")
    @Timed
    public ResponseEntity<List<OfertaZakupu>> getAll(Pageable pageable) {
        final Page<OfertaZakupu> page = ofertaZakupuService.getAllActual(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/oferty-zakupus");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    @GetMapping("/user-oferty-zakupus")
    @Timed
    public ResponseEntity<List<OfertaZakupu>> getAllForUsers(Pageable pageable) {
        final Page<OfertaZakupu> page = ofertaZakupuService.getAllForUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/oferty-zakupus");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
