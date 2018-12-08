import { OfertaZakupu } from '../oferty-gielda-zakup/oferty-gielda-zakup.model';
import { OfertaSprzedazy } from '../oferty-gielda/oferty-gielda.model';

export class Skup {
    constructor(public id: number) {}
}
export class ZrealizowaneZlecenia {
    constructor(
        public id?: number,
        public dataRealizacji?: Date,
        public cena?: number,
        public ilosc?: number,
        public ofertaZakupu?: OfertaZakupu,
        public ofertaSprzedazy?: OfertaSprzedazy
    ) {}
}
