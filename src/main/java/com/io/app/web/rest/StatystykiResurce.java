package com.io.app.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.io.app.service.StatystykiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/sprzedaz-chart")
    @Timed
    public List<Object> getOfertaSprzedazyChart(){
        return this.statystykiService.getOfertaSprzedazyChart();
    }

    @GetMapping("/zakup-chart")
    @Timed
    public List<Object> getOfertaZakupuChart(){
        return this.statystykiService.getOfertaZakupuChart();
    }

    @GetMapping("/zlecenia-chart")
    @Timed
    public List<Object> getZleceniaChart(){
        return this.statystykiService.getZleceniaChart();
    }

    @GetMapping("/zlecenia-chart-sum")
    @Timed
    public List<Object> getSumZleceniaChart(){
        return this.statystykiService.getSumZleceniaChart();
    }

}
