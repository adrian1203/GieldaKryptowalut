import { Routes, RouterModule, Route } from '@angular/router';

import { NgModule } from '../../../../../../node_modules/@angular/core';
import { MojeKontoComponent } from './moje-konto.component';

export const MojeKontoRouter: Route = {
    path: 'moje-konto',
    component: MojeKontoComponent
};
// @NgModule({
//     imports: [RouterModule.forRoot(routes)],
//     exports: [RouterModule]
// })
// export class MojeKontoRouter { }
