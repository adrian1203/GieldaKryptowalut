import { Routes, RouterModule, Route } from '@angular/router';

import { NgModule } from '../../../../../../node_modules/@angular/core';
import { StatystykiComponent } from './statystyki.component';

export const StatystykiRouter: Route = {
    path: 'statystyki',
    component: StatystykiComponent
};
// @NgModule({
//     imports: [RouterModule.forRoot(routes)],
//     exports: [RouterModule]
// })
// export class MojeKontoRouter { }
