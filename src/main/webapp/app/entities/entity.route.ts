import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { MojeKontoRouter } from './moje-konto/moje-konto.router';
import { StatystykiRouter } from './statystyki/statystyki.router';
import { TransakcjeRouter } from './transakcje/transakcje.router';
import { OfertyGieldaRouter } from './oferty-gielda/oferty-gielda.router';
import { KupnoSprzedazRouter } from './kupno-sprzedaz';
import { OfertyGieldaZakupRouter } from './oferty-gielda-zakup/oferty-gielda-zakup.router';
import { KupnoSprzedazNewOfertaZakupComponent } from './kupno-sprzedaz/kupno-sprzedaz-new-oferta-zakup.component';
import {
    KupnoSprzedazNewSprzedazRouter,
    KupnoSprzedazNewZakupRouter,
    OfertyKupnoRouter,
    OfertySprzedazRouter
} from './kupno-sprzedaz/kupno-sprzedaz.router';

const MOJE_KONTO_ROUTES = [MojeKontoRouter];
const STATYSTYKI_ROUTES = [StatystykiRouter];
const TRANSAKCJE_ROUTES = [TransakcjeRouter];
const OFERTY_GIELDA_ROUTES = [OfertyGieldaRouter];
const OFERTY_GIELDA_ZAKUP_ROUTES = [OfertyGieldaZakupRouter];
const KUPNO_SPRZEDAZ_ROUTES = [KupnoSprzedazRouter];
const KUPNO_SPRZEDAZ_NEW_ZAKUP_ROUTES = [KupnoSprzedazNewZakupRouter];
const KUPNO_SPRZEDAZ_NEW_SPRZEDAZ_ROUTES = [KupnoSprzedazNewSprzedazRouter];
const OFERTY_ZAKUP_ROUTES = [OfertyKupnoRouter];
const OFERTY_SPRZEDAZ_ROUTES = [OfertySprzedazRouter];

export const entitiesState: Routes = [
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: MOJE_KONTO_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: STATYSTYKI_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: TRANSAKCJE_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: OFERTY_GIELDA_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: KUPNO_SPRZEDAZ_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: OFERTY_GIELDA_ZAKUP_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: KUPNO_SPRZEDAZ_NEW_ZAKUP_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: KUPNO_SPRZEDAZ_NEW_SPRZEDAZ_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: OFERTY_ZAKUP_ROUTES
    },
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: OFERTY_SPRZEDAZ_ROUTES
    }
];
