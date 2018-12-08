import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { Principal } from 'app/core';
import { HttpResponse } from '@angular/common/http';
import { OfertaSprzedazy } from '../oferty-gielda/oferty-gielda.model';
import { OfertyGieldaService } from '../oferty-gielda/oferty-gielda.service';

@Component({
    selector: 'jhi-oferty-sprzedaz',
    templateUrl: './oferty-sprzedaz.component.html',
    styles: []
})
export class OfertySprzedazComponent implements OnInit {
    currentAccount: any;
    ofertaSprzedazys: OfertaSprzedazy[];
    error: any;
    success: any;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    constructor(
        private ofertyGieldaService: OfertyGieldaService,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private alertService: JhiAlertService,
        private principal: Principal,
        private parseLinks: JhiParseLinks
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });
    }

    ngOnInit() {
        this.page = 1;
        this.loadAllOfertaSprzedazy();
    }
    trackIdentity(index, item: OfertaSprzedazy) {
        return item.id;
    }

    loadAllOfertaSprzedazy() {
        this.ofertyGieldaService
            .queryUserOfertySprzedazy({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<OfertaSprzedazy[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpResponse<any>) => this.onError(res.body)
            );
    }

    private onSuccess(data, headers) {
        console.log(data);
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.ofertaSprzedazys = data;
    }

    private onError(error) {
        //this.alertService.error(error.error, error.message, null);
        console.log('error kurwa');
    }
    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }
    transition() {
        this.router.navigate(['/oferty-gielda'], {
            queryParams: {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAllOfertaSprzedazy();
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
}
