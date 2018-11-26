package com.io.app.service;

import com.io.app.domain.DaneKorespondencyjne;
import com.io.app.repository.DaneKorespondencyjneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DaneKorespondencyjneService {
    private final Logger log = LoggerFactory.getLogger(DaneKorespondencyjneService.class);
    private final DaneKorespondencyjneRepository daneKorespondencyjneRepository;

    public DaneKorespondencyjneService(DaneKorespondencyjneRepository daneKorespondencyjneRepository) {
        this.daneKorespondencyjneRepository = daneKorespondencyjneRepository;
    }

    public List<DaneKorespondencyjne> findAll(){
        return this.daneKorespondencyjneRepository.findAll();
    }
}
