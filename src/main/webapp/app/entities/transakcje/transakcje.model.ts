import { User } from 'app/core';

export class KontoBankowe {
    constructor(public id?: number, public numer?: number, public stanKonta?: number, public waluta?: string, public user?: User) {}
}

export class Przelewy {
    constructor(
        public id?: number,
        public data?: Date,
        public wartosc?: number,
        public waluta?: string,
        public naKontoBankowe?: KontoBankowe,
        public zKontoBankowe?: KontoBankowe
    ) {}
}
