import { Component, OnInit } from '@angular/core';
import { TransakcjeService } from './transakcje.service';
import { KontoBankowe, Przelewy } from './transakcje.model';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiAlertService, JhiParseLinks } from 'ng-jhipster';
import { Principal } from '../../core/auth/principal.service';
import { ITEMS_PER_PAGE } from '../../shared/constants/pagination.constants';
import { HttpResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-transakcje',
    templateUrl: './transakcje.component.html',
    styles: []
})
export class TransakcjeComponent implements OnInit {
    konto: KontoBankowe;
    kontoBTCbalance: number;
    currentAccount: any;
    przelewys: Przelewy[];
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
        private transakcjeService: TransakcjeService,
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
        this.transakcjeService.findKonto().subscribe(res => {
            console.log(res);
            this.konto = res;
        });
        this.transakcjeService.getBTCBalance().subscribe(res => {
            console.log(res);
            this.kontoBTCbalance = res;
        });
        this.page = 1;
        this.loadAllPrzelewy();
    }
    trackIdentity(index, item: Przelewy) {
        return item.id;
    }

    loadAllPrzelewy() {
        this.transakcjeService
            .queryUserOfertySprzedazy({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<Przelewy[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpResponse<any>) => this.onError(res.body)
            );
    }

    private onSuccess(data, headers) {
        console.log(data);
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.przelewys = data;
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
        this.router.navigate(['/transakcje'], {
            queryParams: {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAllPrzelewy();
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
}
