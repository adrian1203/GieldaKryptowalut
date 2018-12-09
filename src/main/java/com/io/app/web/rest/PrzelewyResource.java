package com.io.app.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.Przelewy;
import com.io.app.service.PrzelewyService;
import com.io.app.web.rest.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrzelewyResource {

    private final PrzelewyService przelewyService;

    public PrzelewyResource(PrzelewyService przelewyService) {
        this.przelewyService = przelewyService;
    }

    @GetMapping("/przelewy-user")
    @Timed
    public ResponseEntity<List<Przelewy>> getAllActual(Pageable pageable) {
        final Page<Przelewy> page = przelewyService.getAllForUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/przelewy-user");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
