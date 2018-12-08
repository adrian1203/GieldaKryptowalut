import { Routes, RouterModule, Route } from '@angular/router';

import { NgModule } from '../../../../../../node_modules/@angular/core';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { OfertyGieldaZakupComponent } from './oferty-gielda-zakup.component';

export const OfertyGieldaZakupRouter: Route = {
    path: 'oferty-gielda-zakup',
    component: OfertyGieldaZakupComponent,
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
