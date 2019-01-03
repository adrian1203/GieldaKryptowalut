import { Component, OnInit } from '@angular/core';
import { ZrealizowaneZlecenia } from './kupno-sprzedaz.model';
import { ActivatedRoute, Router } from '@angular/router';
import { KupnoSprzedazService } from './kupno-sprzedaz.service';
import { JhiAlertService, JhiParseLinks } from 'ng-jhipster';
import { Principal } from '../../core/auth/principal.service';
import { ITEMS_PER_PAGE } from '../../shared/constants/pagination.constants';
import { HttpResponse } from '@angular/common/http';
import { UserService } from 'app/core';

@Component({
    selector: 'jhi-kupno-sprzedaz',
    templateUrl: './kupno-sprzedaz.component.html',
    styles: []
})
export class KupnoSprzedazComponent implements OnInit {
    currentAccount: any;
    zrealizowaneZlecenias: ZrealizowaneZlecenia[];
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
    userId: number;
    constructor(
        private kupnoSprzedazService: KupnoSprzedazService,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private alertService: JhiAlertService,
        private principal: Principal,
        private parseLinks: JhiParseLinks,
        private userService: UserService
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
        this.userService.getUserId().subscribe(res => {
            this.userId = res;
            console.log(res);
        });
    }
    trackIdentity(index, item: ZrealizowaneZlecenia) {
        return item.id;
    }

    loadAllOfertaSprzedazy() {
        this.kupnoSprzedazService
            .queryUserZrealizowaneZlecenia({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ZrealizowaneZlecenia[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpResponse<any>) => this.onError(res.body)
            );
    }

    private onSuccess(data, headers) {
        console.log(data);
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.zrealizowaneZlecenias = data;
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
