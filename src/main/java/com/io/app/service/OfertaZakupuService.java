package com.io.app.service;

import com.io.app.domain.OfertaSprzedazy;
import com.io.app.domain.OfertaZakupu;
import com.io.app.repository.OfertaZakupuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfertaZakupuService {
    private final Logger log = LoggerFactory.getLogger(OfertaZakupu.class);
    private final OfertaZakupuRepository ofertaZakupuRepository;

    public OfertaZakupuService(OfertaZakupuRepository ofertaZakupuRepository) {
        this.ofertaZakupuRepository = ofertaZakupuRepository;
    }
    public OfertaZakupu createOfertaZakupu (OfertaZakupu ofertaZakupu ){
        return this.ofertaZakupuRepository.save(ofertaZakupu);
    }
    public List<OfertaZakupu> findAllOfertaZakupu(){
        return this.ofertaZakupuRepository.findAll();
    }
}
