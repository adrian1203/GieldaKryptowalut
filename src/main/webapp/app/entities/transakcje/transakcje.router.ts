import { Routes, RouterModule, Route } from '@angular/router';

import { NgModule } from '../../../../../../node_modules/@angular/core';
import { TransakcjeComponent } from './transakcje.component';
import { JhiResolvePagingParams } from 'ng-jhipster';

export const TransakcjeRouter: Route = {
    path: 'transakcje',
    component: TransakcjeComponent,
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
