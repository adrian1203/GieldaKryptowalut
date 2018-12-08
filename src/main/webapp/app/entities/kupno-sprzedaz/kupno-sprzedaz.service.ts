import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { SERVER_API_URL } from '../../app.constants';
import { OfertaZakupu } from '../oferty-gielda-zakup/oferty-gielda-zakup.model';
import { Observable } from 'rxjs/Rx';
import { OfertaSprzedazy } from '../oferty-gielda/oferty-gielda.model';
import { ZrealizowaneZlecenia } from './kupno-sprzedaz.model';
import { createRequestOption } from '../../shared/util/request-util';

@Injectable({
    providedIn: 'root'
})
export class KupnoSprzedazService {
    private resourceUrl = SERVER_API_URL + 'api/';
    constructor(private http: HttpClient) {}

    createOfertaZakupu(ofertaZakupu: OfertaZakupu): Observable<HttpResponse<OfertaZakupu>> {
        return this.http.post<OfertaZakupu>(this.resourceUrl + `zakup/save`, ofertaZakupu, { observe: 'response' });
    }

    createOfertaSprzedazy(ofertaSprzedazy: OfertaSprzedazy): Observable<HttpResponse<OfertaZakupu>> {
        return this.http.post<OfertaZakupu>(this.resourceUrl + `sprzedaz/save`, ofertaSprzedazy, { observe: 'response' });
    }

    queryUserZrealizowaneZlecenia(req?: any): Observable<HttpResponse<ZrealizowaneZlecenia[]>> {
        const options = createRequestOption(req);
        return this.http.get<ZrealizowaneZlecenia[]>(this.resourceUrl + `zlecenia//findAll-user`, { params: options, observe: 'response' });
    }
}
