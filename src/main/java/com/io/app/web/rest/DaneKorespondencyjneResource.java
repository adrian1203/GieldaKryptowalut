package com.io.app.web.rest;


import com.io.app.domain.DaneKorespondencyjne;
import com.io.app.service.DaneKorespondencyjneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DaneKorespondencyjneResource {
    private final Logger log = LoggerFactory.getLogger(DaneKorespondencyjneResource.class);

    private final DaneKorespondencyjneService daneKorespondencyjneService;

    public DaneKorespondencyjneResource(DaneKorespondencyjneService daneKorespondencyjneService) {
        this.daneKorespondencyjneService = daneKorespondencyjneService;
    }
}
