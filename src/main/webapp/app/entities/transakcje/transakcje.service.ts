import { Injectable } from '@angular/core';
import { SERVER_API_URL } from '../../app.constants';
import { HttpClient } from '@angular/common/http';
import { KontoBankowe } from './transakcje.model';
import { Observable } from 'rxjs/Rx';

@Injectable({
    providedIn: 'root'
})
export class TransakcjeService {
    private resourceUrl = SERVER_API_URL + 'api/';
    constructor(private http: HttpClient) {}

    findKonto(): Observable<KontoBankowe> {
        return this.http.get<KontoBankowe>(SERVER_API_URL + `/api/konto-user`);
    }
}
