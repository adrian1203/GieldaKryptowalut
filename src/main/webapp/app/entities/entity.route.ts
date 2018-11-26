import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { MojeKontoRouter } from './moje-konto/moje-konto.router';

const MOJE_KONTO_ROUTES = [MojeKontoRouter];

export const entitiesState: Routes = [
    {
        path: '',
        canActivate: [UserRouteAccessService],
        children: MOJE_KONTO_ROUTES
    }
];
