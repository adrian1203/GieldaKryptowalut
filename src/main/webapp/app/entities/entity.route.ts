import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { MojeKontoRouter } from './moje-konto/moje-konto.router';
import { StatystykiRouter } from './statystyki/statystyki.router';
import { TransakcjeRouter } from './transakcje/transakcje.router';
import { OfertyGieldaRouter } from './oferty-gielda/oferty-gielda.router';
import { KupnoSprzedazRouter } from './kupno-sprzedaz';

const MOJE_KONTO_ROUTES = [MojeKontoRouter];
const STATYSTYKI_ROUTES = [StatystykiRouter];
const TRANSAKCJE_ROUTES = [TransakcjeRouter];
const OFERTY_GIELDA_ROUTES = [OfertyGieldaRouter];
const KUPNO_SPRZEDAZ_ROUTES = [KupnoSprzedazRouter];

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
    }
];
