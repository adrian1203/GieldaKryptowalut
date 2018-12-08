package com.io.app.service;


import com.io.app.domain.OfertaSprzedazy;
import com.io.app.domain.OfertaZakupu;
import com.io.app.domain.ZrealizowaneZlecenia;
import com.io.app.repository.ZrealizowaneZleceniaRepository;
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
public class ZrealizowaneZleceniaService {
    private final Logger log = LoggerFactory.getLogger(ZrealizowaneZlecenia.class);

    private final ZrealizowaneZleceniaRepository zrealizowaneZleceniaRepository;
    private final UserService userService;
    private final OfertaZakupuService ofertaZakupuService;
    private final OfertaSprzedazyService ofertaSprzedazyService;

    public ZrealizowaneZleceniaService(ZrealizowaneZleceniaRepository zrealizowaneZleceniaRepository, UserService userService, OfertaZakupuService ofertaZakupuService, OfertaSprzedazyService ofertaSprzedazyService) {
        this.zrealizowaneZleceniaRepository = zrealizowaneZleceniaRepository;
        this.userService = userService;
        this.ofertaZakupuService = ofertaZakupuService;
        this.ofertaSprzedazyService = ofertaSprzedazyService;
    }

    public ZrealizowaneZlecenia createZrealizowaneZlecenia(ZrealizowaneZlecenia zrealizowaneZlecenia  ){
        return this.zrealizowaneZleceniaRepository.save(zrealizowaneZlecenia);
    }
    public List<ZrealizowaneZlecenia> findAllZrealizowaneZlecenia(){
        return this.zrealizowaneZleceniaRepository.findAll();
    }
    public Page<ZrealizowaneZlecenia> getAllForUser(Pageable pageable) {
        return zrealizowaneZleceniaRepository.findForUser(this.userService.getUserWithAuthorities().get().getId(),
            pageable);
    }
    public void createZrealizowaneZlecenie(Long ofertaZakupuId, Long ofertaSprzedazyId, Long ilosc, Double cena){
        ZrealizowaneZlecenia zrealizowaneZlecenia = new ZrealizowaneZlecenia();
        OfertaSprzedazy ofertaSprzedazy=this.ofertaSprzedazyService.findById(ofertaSprzedazyId);
        OfertaZakupu ofertaZakupu=this.ofertaZakupuService.findById(ofertaZakupuId);
        ofertaSprzedazy.setPozostalaIlosc(ofertaSprzedazy.getPozostalaIlosc()-ilosc);
        ofertaZakupu.setPozostalaIlosc(ofertaZakupu.getPozostalaIlosc()-ilosc);
        this.ofertaSprzedazyService.update(ofertaSprzedazy);
        this.ofertaZakupuService.update(ofertaZakupu);
        zrealizowaneZlecenia.setCena(cena);
        zrealizowaneZlecenia.setIlosc(ilosc);
        zrealizowaneZlecenia.setOfertaSprzedazy(ofertaSprzedazy);
        zrealizowaneZlecenia.setOfertaZakupu(ofertaZakupu);
        zrealizowaneZlecenia.setDataRealizacji(new Date());
        this.zrealizowaneZleceniaRepository.save(zrealizowaneZlecenia);
    }

    public void kupnoPKC(){
        List<OfertaZakupu> ofertyZakupuPCK=this.ofertaZakupuService.getOfertyZakupuPCK();
        ofertyZakupuPCK.forEach((ofertaZakupu -> {
            List<OfertaSprzedazy> ofertaSprzedazies=this.ofertaSprzedazyService.getOfertySprzedazyLIMIT();

            ofertaSprzedazies.forEach(ofertaSprzedazy -> {
                if(ofertaSprzedazy.getPozostalaIlosc()<=ofertaZakupu.getPozostalaIlosc()){
                    createZrealizowaneZlecenie(ofertaZakupu.getId(),ofertaSprzedazy.getId(), ofertaZakupu.getPozostalaIlosc(),ofertaSprzedazy.getCena());
                    return;
                }else{
                    createZrealizowaneZlecenie(ofertaZakupu.getId(),ofertaSprzedazy.getId(), ofertaZakupu.getPozostalaIlosc(),ofertaSprzedazy.getCena());
                    ofertaZakupu.setPozostalaIlosc(ofertaZakupu.getPozostalaIlosc()-ofertaSprzedazy.getPozostalaIlosc());
                }
            });

        }));

    }
    public void sprzedazPKC(){
        List<OfertaSprzedazy> ofertySprzedazyPKC=this.ofertaSprzedazyService.getOfertySprzedazyPKC();

        ofertySprzedazyPKC.forEach(ofertaSprzedazy -> {
            List<OfertaZakupu> ofertaZakupus = this.ofertaZakupuService.getOfertyZakupuLIMIT();
            ofertaZakupus.forEach(ofertaZakupu -> {
                if(ofertaZakupu.getPozostalaIlosc()>=ofertaSprzedazy.getPozostalaIlosc()){
                    createZrealizowaneZlecenie(ofertaZakupu.getId(),ofertaSprzedazy.getId(),ofertaSprzedazy.getPozostalaIlosc(),ofertaZakupu.getCena());
                    return;
                }else{
                    createZrealizowaneZlecenie(ofertaZakupu.getId(),ofertaSprzedazy.getId(),ofertaSprzedazy.getPozostalaIlosc(),ofertaZakupu.getCena());
                    ofertaSprzedazy.setPozostalaIlosc(ofertaSprzedazy.getPozostalaIlosc()-ofertaZakupu.getPozostalaIlosc());
                }
            });
        });

    }
    public void transakcjeLIMIT(){
        List<OfertaSprzedazy> ofertaSprzedazies = this.ofertaSprzedazyService.getOfertySprzedazyLIMIT();

        ofertaSprzedazies.forEach(ofertaSprzedazy -> {
            List<OfertaZakupu> ofertaZakupus=this.ofertaZakupuService.getOfertyZakupuLIMIT();
            ofertaZakupus.forEach(ofertaZakupu ->{
                if(ofertaSprzedazy.getCena()<ofertaZakupu.getCena()){
                    if(ofertaSprzedazy.getPozostalaIlosc()<=ofertaZakupu.getPozostalaIlosc()){
                        createZrealizowaneZlecenie(ofertaZakupu.getId(),ofertaSprzedazy.getId(), ofertaZakupu.getPozostalaIlosc(),ofertaSprzedazy.getCena());
                        return;
                    }else{
                        createZrealizowaneZlecenie(ofertaZakupu.getId(),ofertaSprzedazy.getId(), ofertaZakupu.getPozostalaIlosc(),ofertaSprzedazy.getCena());
                        ofertaZakupu.setPozostalaIlosc(ofertaZakupu.getPozostalaIlosc()-ofertaSprzedazy.getPozostalaIlosc());
                    }
                }

            });

        });
    }

    public void wykonajTransakcje(){
        this.sprzedazPKC();
        this.kupnoPKC();
        this.transakcjeLIMIT();
    }
}
