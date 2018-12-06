import { Data } from '@angular/router';
import { User } from 'app/core';

export class OfertaSprzedazy {
    constructor(
        public id?: number,
        public data?: String,
        public ilosc?: number,
        public cena?: number,
        public pozostalaIlosc?: number,
        public waluta?: string,
        public user?: User,
        public typZlecenia?: string
    ) {}
}
