package com.io.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.OfertaSprzedazy;
import com.io.app.domain.OfertaZakupu;
import com.io.app.service.OfertaZakupuService;
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
    public ResponseEntity<OfertaZakupu> createOfertaZakupu(@RequestParam OfertaZakupu ofertaZakupu) {

        return ResponseEntity.ok(this.ofertaZakupuService.createOfertaZakupu(ofertaZakupu));

    }
    @GetMapping("findAll")
    public List<OfertaZakupu> findAllOfertaZakupu(){
        return  this.ofertaZakupuService.findAllOfertaZakupu();
    }
}
