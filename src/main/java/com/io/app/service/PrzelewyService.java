package com.io.app.service;


import com.io.app.domain.KontoBankowe;
import com.io.app.domain.Przelewy;
import com.io.app.repository.PrzelewyRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class PrzelewyService {

    public PrzelewyService(PrzelewyRespository przelewyRespository, KontoBankoweService kontoBankoweService, UserService userService) {
        this.przelewyRespository = przelewyRespository;
        this.kontoBankoweService = kontoBankoweService;
        this.userService = userService;
    }

    private final PrzelewyRespository przelewyRespository;
    private final KontoBankoweService kontoBankoweService;
    private final UserService userService;

    public Przelewy createPrzelew(KontoBankowe kontoBankoweFrom, KontoBankowe kontoBankoweTo, Double wartosc){
        Przelewy przelewy = new Przelewy();
        przelewy.setData(new Date());
        przelewy.setWaluta("PLN");
        przelewy.setWartosc(wartosc);
        przelewy.setNaKontoBankowe(kontoBankoweTo);
        przelewy.setzKontoBankowe(kontoBankoweFrom);
        this.kontoBankoweService.updateStanKonta(kontoBankoweFrom, -wartosc);
        this.kontoBankoweService.updateStanKonta(kontoBankoweTo,wartosc);

        return this.przelewyRespository.save(przelewy);

    }
    @Transactional(readOnly = true)
    public Page<Przelewy> getAllForUser(Pageable pageable) {
        return przelewyRespository.findForUser(this.kontoBankoweService.getKonto().getId(), pageable);
    }
}
