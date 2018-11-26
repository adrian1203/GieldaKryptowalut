import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { MojeKonto } from './moje-konto.model';
import { SERVER_API_URL } from '../../app.constants';

@Injectable({
    providedIn: 'root'
})
export class MojeKontoService {
    constructor(public httpCilent: HttpClient) {}
    findAll(): Observable<MojeKonto[]> {
        return this.httpCilent.get<MojeKonto[]>(SERVER_API_URL + `/api/moje-kontas`);
    }
}
