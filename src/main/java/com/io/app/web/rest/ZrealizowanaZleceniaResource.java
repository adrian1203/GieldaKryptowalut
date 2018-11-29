package com.io.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.ZrealizowaneZlecenia;
import com.io.app.service.ZrealizowaneZleceniaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/zlecenia")
public class ZrealizowanaZleceniaResource {
    private final ZrealizowaneZleceniaService zrealizowaneZleceniaService;

    public ZrealizowanaZleceniaResource(ZrealizowaneZleceniaService zrealizowaneZleceniaService) {
        this.zrealizowaneZleceniaService = zrealizowaneZleceniaService;
    }

    @PostMapping("/save")
    @Timed
    public ResponseEntity<ZrealizowaneZlecenia> createOfertaSprzedarzy(@RequestParam ZrealizowaneZlecenia zrealizowaneZlecenia) {

        return ResponseEntity.ok(this.zrealizowaneZleceniaService.createZrealizowaneZlecenia(zrealizowaneZlecenia));

    }
    @GetMapping("findAll")
    public List<ZrealizowaneZlecenia> findAllOfertaSprzedazy(){
        return  this.zrealizowaneZleceniaService.findAllZrealizowaneZlecenia();
    }
}
