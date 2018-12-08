import { Injectable } from '@angular/core';
import { OfertaSprzedazy } from './oferty-gielda.model';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class OfertyGieldaService {
    private resourceUrl = SERVER_API_URL + 'api/sprzedaz/';
    constructor(private http: HttpClient) {}

    queryOfertySprzedazy(req?: any): Observable<HttpResponse<OfertaSprzedazy[]>> {
        const options = createRequestOption(req);
        return this.http.get<OfertaSprzedazy[]>(this.resourceUrl + `oferty-sprzedazys`, { params: options, observe: 'response' });
    }

    queryUserOfertySprzedazy(req?: any): Observable<HttpResponse<OfertaSprzedazy[]>> {
        const options = createRequestOption(req);
        return this.http.get<OfertaSprzedazy[]>(this.resourceUrl + `user-oferty-sprzedazys`, { params: options, observe: 'response' });
    }
}
