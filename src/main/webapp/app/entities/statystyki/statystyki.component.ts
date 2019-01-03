import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../../../CanvasJS/canvasjs.min';
import { StatystykiService } from 'app/entities/statystyki/statystyki.service';

@Component({
    selector: 'jhi-statystyki',
    templateUrl: './statystyki.component.html',
    styles: []
})
export class StatystykiComponent implements OnInit {
    kursKupna: number;
    kursSprzedaz: number;
    constructor(public service: StatystykiService) {}

    ngOnInit() {
        this.getKursy();
        this.service.getOfertaSprzedazyChart().subscribe(res => {
            let dataPoints = [];
            console.log(res);
            res.forEach(elem => {
                dataPoints.push({ y: elem[1], label: elem[0] });
            });
            this.drawOfartaSprzedazyChart(dataPoints);
        });
        this.service.getOfertaZakupuChart().subscribe(res => {
            let dataPoints = [];
            console.log(res);
            res.forEach(elem => {
                dataPoints.push({ y: elem[1], label: elem[0] });
            });
            this.drawOfartaZakupuChart(dataPoints);
        });
        this.service.getZlecenieChart().subscribe(res => {
            let dataPoints = [];
            console.log(res);
            res.forEach(elem => {
                dataPoints.push({ y: elem[1], label: elem[0] });
            });
            this.drawZleceniaChart(dataPoints);
        });
        this.service.getSumZlecenieChart().subscribe(res => {
            let dataPoints = [];
            console.log(res);
            res.forEach(elem => {
                dataPoints.push({ y: elem[1], label: elem[0] });
            });
            this.drawSumZleceniaChart(dataPoints);
        });
    }
    getKursy() {
        this.service.getKursKupno().subscribe(res => {
            this.kursKupna = res;
        });
        this.service.getKursSprzedaz().subscribe(res => {
            this.kursSprzedaz = res;
        });
    }
    drawOfartaSprzedazyChart(dataPoints: any) {
        let chart = new CanvasJS.Chart('chartSprzedaz', {
            animationEnabled: true,
            exportEnabled: true,
            title: {
                text: 'Ilość ofert sprzedaży w poszczególnych dniach'
            },
            data: [
                {
                    type: 'line',
                    dataPoints: dataPoints
                }
            ]
        });

        chart.render();
    }

    drawOfartaZakupuChart(dataPoints: any) {
        let chart = new CanvasJS.Chart('chartZakup', {
            animationEnabled: true,
            exportEnabled: true,
            title: {
                text: 'Ilość ofert zakupu w poszczególnych dniach'
            },
            toolTip: {
                shared: true
            },
            data: [
                {
                    type: 'line',
                    dataPoints: dataPoints
                }
            ]
        });

        chart.render();
    }

    drawZleceniaChart(dataPoints: any) {
        let chart = new CanvasJS.Chart('chartZlecenia', {
            animationEnabled: true,
            exportEnabled: true,
            title: {
                text: 'Ilość zrealizowanych zleceń w poszczególnych dniach'
            },
            data: [
                {
                    type: 'line',
                    dataPoints: dataPoints
                }
            ]
        });

        chart.render();
    }
    drawSumZleceniaChart(dataPoints: any) {
        let chart = new CanvasJS.Chart('chartSumZlecenia', {
            animationEnabled: true,
            exportEnabled: true,
            title: {
                text: 'Suma zrealizowanych zleceń w poszczególnych dniach'
            },
            data: [
                {
                    type: 'line',
                    dataPoints: dataPoints
                }
            ]
        });

        chart.render();
    }
}
