import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
// import { MojeKontoModule } from './moje-konto/moje-konto.module';
import { RouterModule } from '@angular/router';
import { MojeKontoComponent } from './moje-konto/moje-konto.component';
import { GieldaKryptowalutSharedModule } from '../shared/shared.module';
import { entitiesState } from './entity.route';
import { KupnoSprzedazComponent } from './kupno-sprzedaz/kupno-sprzedaz.component';
//import {entitiesState} from "./entity.route";

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        GieldaKryptowalutSharedModule,
        RouterModule.forChild(entitiesState)

        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [MojeKontoComponent, KupnoSprzedazComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GieldaKryptowalutEntityModule {}
