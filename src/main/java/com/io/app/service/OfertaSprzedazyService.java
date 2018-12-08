package com.io.app.service;


import com.io.app.domain.OfertaSprzedazy;
import com.io.app.repository.OfertaSprzedazyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OfertaSprzedazyService {
    private final Logger log = LoggerFactory.getLogger(OfertaSprzedazy.class);
    private final OfertaSprzedazyRepository ofertaSprzedazyRepository;
    private final UserService userService;

    public OfertaSprzedazyService(OfertaSprzedazyRepository ofertaSprzedazyRepository, UserService userService) {
        this.ofertaSprzedazyRepository = ofertaSprzedazyRepository;
        this.userService = userService;
    }
    public OfertaSprzedazy createOfertaSprzedarzy (OfertaSprzedazy ofertaSprzedazy){
        ofertaSprzedazy.setUser(this.userService.getUserWithAuthorities().get());
        ofertaSprzedazy.setDataWystawienia(new Date());
        ofertaSprzedazy.setWaluta("PLN");
        ofertaSprzedazy.setPozostalaIlosc(ofertaSprzedazy.getIlosc());
        return this.ofertaSprzedazyRepository.save(ofertaSprzedazy);
    }
    public List<OfertaSprzedazy> findAllOfertaSprzedazy(){
        return this.ofertaSprzedazyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<OfertaSprzedazy> getAllOfertSprzedazy(Pageable pageable) {
        return ofertaSprzedazyRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Page<OfertaSprzedazy> getActualOfertSprzedazy(Pageable pageable) {
        return ofertaSprzedazyRepository.findActual(pageable);
    }

    @Transactional(readOnly = true)
    public Page<OfertaSprzedazy> getForUser(Pageable pageable) {
        return ofertaSprzedazyRepository.findForUser(this.userService.getUserWithAuthorities().get().getId()
        ,pageable);
    }


}
