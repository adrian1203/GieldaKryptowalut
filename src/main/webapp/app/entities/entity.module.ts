import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
// import { MojeKontoModule } from './moje-konto/moje-konto.module';
import { RouterModule } from '@angular/router';
import { MojeKontoComponent } from './moje-konto/moje-konto.component';
import { GieldaKryptowalutSharedModule } from '../shared/shared.module';
import { entitiesState } from './entity.route';
import { KupnoSprzedazComponent } from './kupno-sprzedaz/kupno-sprzedaz.component';
import { StatystykiComponent } from './statystyki/statystyki.component';
import { TransakcjeComponent } from './transakcje/transakcje.component';
import { OfertyGieldaComponent } from './oferty-gielda/oferty-gielda.component';
import { OfertyGieldaZakupComponent } from './oferty-gielda-zakup/oferty-gielda-zakup.component';
import { KupnoSprzedazNewOfertaZakupComponent } from './kupno-sprzedaz/kupno-sprzedaz-new-oferta-zakup.component';
import { KupnoSprzedazNewOfertaSprzedazComponent } from './kupno-sprzedaz/kupno-sprzedaz-new-oferta-sprzedaz.component';
import { OfertySprzedazComponent } from './kupno-sprzedaz/oferty-sprzedaz.component';
import { OfertyZakupComponent } from './kupno-sprzedaz/oferty-zakup.component';
//import {entitiesState} from "./entity.route";

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        GieldaKryptowalutSharedModule,
        RouterModule.forChild(entitiesState)

        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [
        MojeKontoComponent,
        KupnoSprzedazComponent,
        StatystykiComponent,
        TransakcjeComponent,
        OfertyGieldaComponent,
        OfertyGieldaZakupComponent,
        KupnoSprzedazNewOfertaZakupComponent,
        KupnoSprzedazNewOfertaSprzedazComponent,
        OfertySprzedazComponent,
        OfertyZakupComponent
    ],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GieldaKryptowalutEntityModule {}
