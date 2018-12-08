import { Routes, RouterModule, Route } from '@angular/router';

import { NgModule } from '../../../../../../node_modules/@angular/core';
import { KupnoSprzedazComponent } from './kupno-sprzedaz.component';
import { KupnoSprzedazNewOfertaZakupComponent } from './kupno-sprzedaz-new-oferta-zakup.component';
import { KupnoSprzedazNewOfertaSprzedazComponent } from './kupno-sprzedaz-new-oferta-sprzedaz.component';
import { OfertySprzedazComponent } from 'app/entities/kupno-sprzedaz/oferty-sprzedaz.component';
import { OfertyZakupComponent } from './oferty-zakup.component';
import { JhiResolvePagingParams } from 'ng-jhipster';

export const KupnoSprzedazRouter: Route = {
    path: 'kupno-sprzedaz',
    component: KupnoSprzedazComponent,
    resolve: {
        pagingParams: JhiResolvePagingParams
    },
    data: {
        pageTitle: 'userManagement.home.title',
        defaultSort: 'id,asc'
    }
};
export const KupnoSprzedazNewZakupRouter: Route = {
    path: 'kupno-sprzedaz-new-zakup',
    component: KupnoSprzedazNewOfertaZakupComponent
};
export const KupnoSprzedazNewSprzedazRouter: Route = {
    path: 'kupno-sprzedaz-new-sprzedaz',
    component: KupnoSprzedazNewOfertaSprzedazComponent
};
export const OfertyKupnoRouter: Route = {
    path: 'kupno-oferty',
    component: OfertyZakupComponent,
    resolve: {
        pagingParams: JhiResolvePagingParams
    },
    data: {
        pageTitle: 'userManagement.home.title',
        defaultSort: 'id,asc'
    }
};
export const OfertySprzedazRouter: Route = {
    path: 'sprzedaz-oferty',
    component: OfertySprzedazComponent,
    resolve: {
        pagingParams: JhiResolvePagingParams
    },
    data: {
        pageTitle: 'userManagement.home.title',
        defaultSort: 'id,asc'
    }
};

// @NgModule({
//     imports: [RouterModule.forRoot(routes)],
//     exports: [RouterModule]
// })
// export class MojeKontoRouter { }
