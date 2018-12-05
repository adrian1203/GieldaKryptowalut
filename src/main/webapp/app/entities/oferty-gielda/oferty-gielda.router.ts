import { Routes, RouterModule, Route } from '@angular/router';

import { NgModule } from '../../../../../../node_modules/@angular/core';
import { OfertyGieldaComponent } from './oferty-gielda.component';

export const OfertyGieldaRouter: Route = {
    path: 'oferty-gielda',
    component: OfertyGieldaComponent
};
// @NgModule({
//     imports: [RouterModule.forRoot(routes)],
//     exports: [RouterModule]
// })
// export class MojeKontoRouter { }
