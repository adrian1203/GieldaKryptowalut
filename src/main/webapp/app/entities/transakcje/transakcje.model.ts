import { User } from 'app/core';

export class KontoBankowe {
    constructor(public id?: number, public numer?: number, public stanKonta?: number, public waluta?: string, public user?: User) {}
}
