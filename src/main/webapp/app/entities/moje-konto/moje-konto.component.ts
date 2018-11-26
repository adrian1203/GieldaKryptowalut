import { Component, OnInit } from '@angular/core';
import { MojeKontoService } from './moje-konto.service';

@Component({
    selector: 'jhi-moje-konto',
    templateUrl: './moje-konto.component.html',
    styles: []
})
export class MojeKontoComponent implements OnInit {
    constructor(public mojeKontoService: MojeKontoService) {}

    ngOnInit() {
        // this.mojeKontoService.findAll().subscribe(res=>{
        //     console.log(res);
        // })
    }
}
