import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { SERVER_API_URL } from '../../app.constants';
import { OfertaZakupu } from './oferty-gielda-zakup.model';
import { createRequestOption } from '../../shared/util/request-util';

@Injectable({
    providedIn: 'root'
})
export class OfertyGieldaZakupService {
    private resourceUrl = SERVER_API_URL + 'api/zakup/';
    constructor(private http: HttpClient) {}

    queryOfertyZakupu(req?: any): Observable<HttpResponse<OfertaZakupu[]>> {
        const options = createRequestOption(req);
        return this.http.get<OfertaZakupu[]>(this.resourceUrl + `oferty-zakupus`, { params: options, observe: 'response' });
    }

    queryUserOfertyZakupu(req?: any): Observable<HttpResponse<OfertaZakupu[]>> {
        const options = createRequestOption(req);
        return this.http.get<OfertaZakupu[]>(this.resourceUrl + `user-oferty-zakupus`, { params: options, observe: 'response' });
    }
}
