package com.io.app.service;

import com.io.app.repository.DaneKorespondencyjneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DaneKorespondencyjneService {
    private final Logger log = LoggerFactory.getLogger(DaneKorespondencyjneService.class);
    private final DaneKorespondencyjneRepository daneKorespondencyjneRepository;

    public DaneKorespondencyjneService(DaneKorespondencyjneRepository daneKorespondencyjneRepository) {
        this.daneKorespondencyjneRepository = daneKorespondencyjneRepository;
    }
}
