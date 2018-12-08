import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../core/user/user.service';
import { User } from '../../core/user/user.model';
import { OfertaZakupu } from '../oferty-gielda-zakup/oferty-gielda-zakup.model';
import { KupnoSprzedazService } from './kupno-sprzedaz.service';
import { Principal, AccountService, JhiLanguageHelper } from 'app/core';
@Component({
    selector: 'jhi-kupno-sprzedaz-new-zakup',
    templateUrl: './kupno-sprzedaz-new-oferta-zakup.component.html',
    styles: []
})
export class KupnoSprzedazNewOfertaZakupComponent implements OnInit {
    user: User;
    ofertaZakupu: OfertaZakupu;
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
        this.ofertaZakupu = new OfertaZakupu();
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
        // this.ofertaZakupu.user=this.user;
        this.kupnoSprzedazService
            .createOfertaZakupu(this.ofertaZakupu)
            .subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this.previousState();
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
