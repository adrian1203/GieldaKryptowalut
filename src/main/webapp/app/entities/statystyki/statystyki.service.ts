import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { KontoBankowe } from 'app/entities/transakcje/transakcje.model';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class StatystykiService {
    private resourceUrl = SERVER_API_URL + 'api/';
    constructor(private http: HttpClient) {}

    getKursKupno(): Observable<number> {
        return this.http.get<number>(this.resourceUrl + `/statystyki/kurs-kupno`);
    }
    getKursSprzedaz(): Observable<number> {
        return this.http.get<number>(this.resourceUrl + `/statystyki/kurs-sprzedaz`);
    }
}
