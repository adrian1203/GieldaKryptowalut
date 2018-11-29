package com.io.app.service;


import com.io.app.domain.OfertaSprzedazy;
import com.io.app.repository.OfertaSprzedarzyRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OfertaSprzedarzyService {
    private final Logger log = LoggerFactory.getLogger(OfertaSprzedazy.class);
    private final OfertaSprzedarzyRepository ofertaSprzedarzyRepository;

    public OfertaSprzedarzyService(OfertaSprzedarzyRepository ofertaSprzedarzyRepository) {
        this.ofertaSprzedarzyRepository = ofertaSprzedarzyRepository;
    }
    public OfertaSprzedazy createOfertaSprzedarzy (OfertaSprzedazy ofertaSprzedazy){
        return this.ofertaSprzedarzyRepository.save(ofertaSprzedazy);
    }
    public List<OfertaSprzedazy> findAllOfertaSprzedazy(){
        return this.ofertaSprzedarzyRepository.findAll();
    }


}
