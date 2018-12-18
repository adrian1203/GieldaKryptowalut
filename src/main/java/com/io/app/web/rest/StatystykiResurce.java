package com.io.app.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.io.app.service.StatystykiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statystyki")
public class StatystykiResurce {
   private final StatystykiService statystykiService;

    public StatystykiResurce(StatystykiService statystykiService) {
        this.statystykiService = statystykiService;
    }


    @GetMapping("/kurs-kupno")
    @Timed
    public Double getKursKupna(){
        return this.statystykiService.getKursKupno();
    }

    @GetMapping("/kurs-sprzedaz")
    @Timed
    public Double getKursSprzedazy(){
        return this.statystykiService.getKursSprzedazy();
    }
}
