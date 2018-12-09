package com.io.app.service;

import com.io.app.domain.KontoBankowe;
import com.io.app.domain.User;
import com.io.app.repository.KontoBankoweRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class KontoBankoweService {

    private final KontoBankoweRepository kontoBankoweRepository;

    private final UserService userService;

    public KontoBankoweService(KontoBankoweRepository kontoBankoweRepository, UserService userService) {
        this.kontoBankoweRepository = kontoBankoweRepository;
        this.userService = userService;
    }
    public void createNewKonto(User user){
        KontoBankowe kontoBankowe = new KontoBankowe();
        kontoBankowe.setUser(user);
        kontoBankowe.setWaluta("PLN");
        kontoBankowe.setStanKonta(5000);
        kontoBankowe.setNumer("33333333333333");

        this.kontoBankoweRepository.save(kontoBankowe);

    }
    public KontoBankowe getKonto(){
        return this.kontoBankoweRepository.findKontoBankowe(this.userService.getUserWithAuthorities().get().getId()).get(0);
    }

    public KontoBankowe getKontoByUserId(Long id){
        return this.kontoBankoweRepository.findKontoBankowe(id).get(0);
    }
    public KontoBankowe updateStanKonta(KontoBankowe kontoBankowe, Double wartosc){
        kontoBankowe.setStanKonta(kontoBankowe.getStanKonta()+wartosc);

        return this.kontoBankoweRepository.save(kontoBankowe);
    }
}
