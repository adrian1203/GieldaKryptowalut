package com.io.app.service;

import com.io.app.domain.OfertaZakupu;
import com.io.app.repository.OfertaZakupuRepository;
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
public class OfertaZakupuService {
    private final Logger log = LoggerFactory.getLogger(OfertaZakupu.class);
    private final OfertaZakupuRepository ofertaZakupuRepository;
    private final UserService userService;

    public OfertaZakupuService(OfertaZakupuRepository ofertaZakupuRepository, UserService userService) {
        this.ofertaZakupuRepository = ofertaZakupuRepository;
        this.userService = userService;
    }
    public OfertaZakupu createOfertaZakupu (OfertaZakupu ofertaZakupu ){
        ofertaZakupu.setDataWystawienia(new Date());
        ofertaZakupu.setPozostalaIlosc(ofertaZakupu.getIlosc());
        ofertaZakupu.setWaluta("PLN");
        ofertaZakupu.setUser(this.userService.getUserWithAuthorities().get());
        return this.ofertaZakupuRepository.save(ofertaZakupu);
    }
    public List<OfertaZakupu> findAllOfertaZakupu(){
        return this.ofertaZakupuRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<OfertaZakupu> getAllOfertSprzedazy(Pageable pageable) {
        return ofertaZakupuRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Page<OfertaZakupu> getAllActual(Pageable pageable) {
        return ofertaZakupuRepository.findActual(pageable);
    }
    @Transactional(readOnly = true)
    public Page<OfertaZakupu> getAllForUser(Pageable pageable) {
        return ofertaZakupuRepository.findForUser(this.userService.getUserWithAuthorities().get().getId(),
            pageable);
    }
}
