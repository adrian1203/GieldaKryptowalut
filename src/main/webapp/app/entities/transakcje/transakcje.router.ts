import { Routes, RouterModule, Route } from '@angular/router';

import { NgModule } from '../../../../../../node_modules/@angular/core';
import { TransakcjeComponent } from './transakcje.component';

export const TransakcjeRouter: Route = {
    path: 'transakcje',
    component: TransakcjeComponent
};
// @NgModule({
//     imports: [RouterModule.forRoot(routes)],
//     exports: [RouterModule]
// })
// export class MojeKontoRouter { }
