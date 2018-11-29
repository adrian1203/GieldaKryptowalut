package com.io.app.service;


import com.io.app.domain.OfertaSprzedazy;
import com.io.app.repository.OfertaSprzedazyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfertaSprzedazyService {
    private final Logger log = LoggerFactory.getLogger(OfertaSprzedazy.class);
    private final OfertaSprzedazyRepository ofertaSprzedazyRepository;

    public OfertaSprzedazyService(OfertaSprzedazyRepository ofertaSprzedazyRepository) {
        this.ofertaSprzedazyRepository = ofertaSprzedazyRepository;
    }
    public OfertaSprzedazy createOfertaSprzedarzy (OfertaSprzedazy ofertaSprzedazy){
        return this.ofertaSprzedazyRepository.save(ofertaSprzedazy);
    }
    public List<OfertaSprzedazy> findAllOfertaSprzedazy(){
        return this.ofertaSprzedazyRepository.findAll();
    }


}
