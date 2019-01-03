import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../core/user/user.service';
import { User } from '../../core/user/user.model';
import { OfertaZakupu } from '../oferty-gielda-zakup/oferty-gielda-zakup.model';
import { KupnoSprzedazService } from './kupno-sprzedaz.service';
import { Principal, AccountService, JhiLanguageHelper } from 'app/core';
import { OfertaSprzedazy } from '../oferty-gielda/oferty-gielda.model';
@Component({
    selector: 'jhi-kupno-sprzedaz-new-sprzedaz',
    templateUrl: './kupno-sprzedaz-new-oferta-sprzedaz.component.html',
    styles: []
})
export class KupnoSprzedazNewOfertaSprzedazComponent implements OnInit {
    user: User;
    ofertaSprzedazy: OfertaSprzedazy;
    languages: any[];
    authorities: any[];
    isSaving: boolean;
    settingsAccount: any;

    constructor(
        private userService: UserService,
        private route: ActivatedRoute,
        private router: Router,
        private principal: Principal,
        private kupnoSprzedazService: KupnoSprzedazService
    ) {
        this.ofertaSprzedazy = new OfertaSprzedazy();
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = [];
        this.userService.authorities().subscribe(authorities => {
            this.authorities = authorities;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        console.log(this.settingsAccount);
        this.isSaving = true;
        if (this.ofertaSprzedazy.typZlecenia === 'PKC') {
            this.ofertaSprzedazy.cena = null;
        }
        // this.ofertaZakupu.user=this.user;
        this.kupnoSprzedazService
            .createOfertaSprzedazy(this.ofertaSprzedazy)
            .subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this.previousState();
        this.kupnoSprzedazService.wykonajZlecenie();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    copyAccount(account) {
        return {
            activated: account.activated,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl
        };
    }
}
