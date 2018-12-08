import { Data } from '@angular/router';
import { User } from 'app/core';

export class OfertaZakupu {
    constructor(
        public id?: number,
        public dataWystawienia?: Date,
        public ilosc?: number,
        public cena?: number,
        public pozostalaIlosc?: number,
        public waluta?: string,
        public user?: User,
        public typZlecenia?: string
    ) {}
}
