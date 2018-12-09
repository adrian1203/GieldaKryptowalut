import { Component, OnInit } from '@angular/core';
import { TransakcjeService } from './transakcje.service';
import { KontoBankowe } from './transakcje.model';

@Component({
    selector: 'jhi-transakcje',
    templateUrl: './transakcje.component.html',
    styles: []
})
export class TransakcjeComponent implements OnInit {
    konto: KontoBankowe;
    constructor(private transakcjeService: TransakcjeService) {}

    ngOnInit() {
        this.transakcjeService.findKonto().subscribe(res => {
            console.log(res);
            this.konto = res;
        });
    }
}
