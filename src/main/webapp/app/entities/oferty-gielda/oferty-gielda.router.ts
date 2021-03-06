import { Routes, RouterModule, Route } from '@angular/router';

import { NgModule } from '../../../../../../node_modules/@angular/core';
import { OfertyGieldaComponent } from './oferty-gielda.component';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';

export const OfertyGieldaRouter: Route = {
    path: 'oferty-gielda',
    component: OfertyGieldaComponent,
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
