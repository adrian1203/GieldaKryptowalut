import { Injectable } from '@angular/core';
import { SERVER_API_URL } from '../../app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { KontoBankowe, Przelewy } from './transakcje.model';
import { Observable } from 'rxjs/Rx';
import { createRequestOption } from '../../shared/util/request-util';

@Injectable({
    providedIn: 'root'
})
export class TransakcjeService {
    private resourceUrl = SERVER_API_URL + 'api/';
    constructor(private http: HttpClient) {}

    findKonto(): Observable<KontoBankowe> {
        return this.http.get<KontoBankowe>(SERVER_API_URL + `/api/konto-user`);
    }

    getBTCBalance(): Observable<number> {
        return this.http.get<number>(SERVER_API_URL + `/api/kontoBTC-user`);
    }
    queryUserOfertySprzedazy(req?: any): Observable<HttpResponse<Przelewy[]>> {
        const options = createRequestOption(req);
        return this.http.get<Przelewy[]>(this.resourceUrl + `przelewy-user`, { params: options, observe: 'response' });
    }
    getAddress(): Observable<HttpResponse<string>> {
        return this.http.get<string>(this.resourceUrl + `kontoBTC-address`, { observe: 'response' });
    }
    refresh() {
        console.log('chuja2');
        return this.http.get<any>(this.resourceUrl + `kontoBTC-refresh`);
    }
}
