package com.io.app.service;


import com.io.app.domain.*;
import com.io.app.repository.ZrealizowaneZleceniaRepository;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.wallet.Wallet;
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
    private final CryptocService cryptocService;
    private final KontoBankoweService kontoBankoweService;
    private final PrzelewyService przelewyService;

    public ZrealizowaneZleceniaService(ZrealizowaneZleceniaRepository zrealizowaneZleceniaRepository, UserService userService, OfertaZakupuService ofertaZakupuService, OfertaSprzedazyService ofertaSprzedazyService, CryptocService cryptocService, KontoBankoweService kontoBankoweService, PrzelewyService przelewyService) {
        this.zrealizowaneZleceniaRepository = zrealizowaneZleceniaRepository;
        this.userService = userService;
        this.ofertaZakupuService = ofertaZakupuService;
        this.ofertaSprzedazyService = ofertaSprzedazyService;
        this.cryptocService = cryptocService;
        this.kontoBankoweService = kontoBankoweService;
        this.przelewyService = przelewyService;
    }

    public ZrealizowaneZlecenia createZrealizowaneZlecenia(ZrealizowaneZlecenia zrealizowaneZlecenia) {
        return this.zrealizowaneZleceniaRepository.save(zrealizowaneZlecenia);
    }

    public List<ZrealizowaneZlecenia> findAllZrealizowaneZlecenia() {
        return this.zrealizowaneZleceniaRepository.findAll();
    }

    public Page<ZrealizowaneZlecenia> getAllForUser(Pageable pageable) {
        return zrealizowaneZleceniaRepository.findForUser(this.userService.getUserWithAuthorities().get().getId(),
            pageable);
    }

    public void realizeTransfer(OfertaZakupu ofertaZakupu, OfertaSprzedazy ofertaSprzedazy,  Long ilosc, Double cena){

        if(cena==0){cena=ofertaZakupu.getCena();}
        if(checkStanKonta(ofertaZakupu.getUser(),ilosc, cena) || checkWalletBTC(ofertaSprzedazy.getUser(), ilosc)){

            //TODO odkomentować createBTCTeanser i zmienić || na && ^^ gdy będziemy mieć bitcoiny na koncie xD
            this.createZrealizowaneZlecenie(ofertaZakupu.getId(),ofertaSprzedazy.getId(),ilosc, cena);
           // this.createBTCTransfer(ofertaSprzedazy.getUser().getLogin(),ofertaZakupu.getUser().getLogin(), ilosc);
            this.createKontoBankoweTransfer(ofertaZakupu.getUser(), ofertaSprzedazy.getUser(),ilosc,cena);

        }



    }
    public boolean checkStanKonta(User user, Long ilosc, Double cena){
        return this.kontoBankoweService.getKontoByUserId(user.getId()).getStanKonta()>=ilosc*cena;
    }

    public boolean checkWalletBTC(User user, Long ilosc){
        return  this.cryptocService.GetWalletBalance(user.getLogin(),RegTestParams.get())>=ilosc;
    }
    public void createKontoBankoweTransfer(User UserOfertaZakupu, User UserOfertaSprzedazy, Long ilosc, Double cena ){

        //z tego robimy przelew
        KontoBankowe kontoBankoweZakupu = this.kontoBankoweService.getKontoByUserId(UserOfertaSprzedazy.getId());
        KontoBankowe kontoBankoweSprzedazy = this.kontoBankoweService.getKontoByUserId(UserOfertaZakupu.getId());
        this.przelewyService.createPrzelew(kontoBankoweZakupu,kontoBankoweSprzedazy,cena*ilosc);

    }


    public void createZrealizowaneZlecenie(Long ofertaZakupuId, Long ofertaSprzedazyId, Long ilosc, Double cena) {
        ZrealizowaneZlecenia zrealizowaneZlecenia = new ZrealizowaneZlecenia();
        OfertaSprzedazy ofertaSprzedazy = this.ofertaSprzedazyService.findById(ofertaSprzedazyId);
        OfertaZakupu ofertaZakupu = this.ofertaZakupuService.findById(ofertaZakupuId);
        ofertaSprzedazy.setPozostalaIlosc(ofertaSprzedazy.getPozostalaIlosc() - ilosc);
        ofertaZakupu.setPozostalaIlosc(ofertaZakupu.getPozostalaIlosc() - ilosc);
        this.ofertaSprzedazyService.update(ofertaSprzedazy);
        this.ofertaZakupuService.update(ofertaZakupu);
        zrealizowaneZlecenia.setCena(cena);
        zrealizowaneZlecenia.setIlosc(ilosc);
        zrealizowaneZlecenia.setOfertaSprzedazy(ofertaSprzedazy);
        zrealizowaneZlecenia.setOfertaZakupu(ofertaZakupu);
        zrealizowaneZlecenia.setDataRealizacji(new Date());
        this.zrealizowaneZleceniaRepository.save(zrealizowaneZlecenia);
    }

    public void createBTCTransfer(String senderLogin, String recipientLogin, Long ilosc ){
        Wallet senderWallet= this.cryptocService.LoadWallet(senderLogin, RegTestParams.get());
        String respientAdress = this.cryptocService.GetWalletAdress(recipientLogin,RegTestParams.get());
        this.cryptocService.SendToAdress(senderWallet,senderLogin,RegTestParams.get(),ilosc,respientAdress);

    }

    public void kupnoPKC() {
        List<OfertaZakupu> ofertyZakupuPCK = this.ofertaZakupuService.getOfertyZakupuPCK();
        ofertyZakupuPCK.forEach((ofertaZakupu -> {
            List<OfertaSprzedazy> ofertaSprzedazies = this.ofertaSprzedazyService.getOfertySprzedazyLIMIT();

            ofertaSprzedazies.forEach(ofertaSprzedazy -> {
                if (!check(ofertaSprzedazy, ofertaZakupu, true)) {
                    return;
                }

            });

        }));

    }

    public void sprzedazPKC() {
        List<OfertaSprzedazy> ofertySprzedazyPKC = this.ofertaSprzedazyService.getOfertySprzedazyPKC();

        ofertySprzedazyPKC.forEach(ofertaSprzedazy -> {
            List<OfertaZakupu> ofertaZakupus = this.ofertaZakupuService.getOfertyZakupuLIMIT();
            ofertaZakupus.forEach(ofertaZakupu -> {
                if (!check(ofertaSprzedazy, ofertaZakupu, true)) {
                    return;
                }

            });

        });
    }

    public void transakcjeLIMIT() {
        List<OfertaSprzedazy> ofertaSprzedazies = this.ofertaSprzedazyService.getOfertySprzedazyLIMIT();

        ofertaSprzedazies.forEach(ofertaSprzedazy -> {
            List<OfertaZakupu> ofertaZakupus = this.ofertaZakupuService.getOfertyZakupuLIMIT();
            ofertaZakupus.forEach(ofertaZakupu -> {
                if (!check(ofertaSprzedazy, ofertaZakupu, false)) {
                    return;
                }
            });

        });
    }

    public boolean check(OfertaSprzedazy ofertaSprzedazy, OfertaZakupu ofertaZakupu, boolean isPKC) {
        if (ofertaSprzedazy.getUser() == ofertaZakupu.getUser()) {
            return false;
        }
        if (ofertaSprzedazy.getCena() < ofertaZakupu.getCena() || isPKC) {
            if (ofertaSprzedazy.getPozostalaIlosc() >= ofertaZakupu.getPozostalaIlosc()) {
                realizeTransfer(ofertaZakupu, ofertaSprzedazy, ofertaZakupu.getPozostalaIlosc(), ofertaSprzedazy.getCena());
                return false;
            } else {
                realizeTransfer(ofertaZakupu, ofertaSprzedazy, ofertaSprzedazy.getPozostalaIlosc(), ofertaSprzedazy.getCena());
                //ofertaZakupu.setPozostalaIlosc(0L);
                return true;
            }
        }
        return true;
    }

    public void wykonajTransakcje() {
        this.sprzedazPKC();
        this.kupnoPKC();
        this.transakcjeLIMIT();
    }
}
