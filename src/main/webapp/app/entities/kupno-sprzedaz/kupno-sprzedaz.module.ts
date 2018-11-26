import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { KupnoSprzedazComponent } from './kupno-sprzedaz.component';
import { KupnoSprzedazRouter } from './kupno-sprzedaz.router';

@NgModule({
    imports: [CommonModule, KupnoSprzedazRouter],
    declarations: [KupnoSprzedazComponent],
    bootstrap: [KupnoSprzedazComponent],
    exports: [KupnoSprzedazComponent]
})
export class MojeKontoModule {}
