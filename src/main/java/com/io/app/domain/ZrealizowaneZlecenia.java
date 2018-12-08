package com.io.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "zrealizowane_zlecenia")
public class ZrealizowaneZlecenia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


    @Column(name = "data_realizacji")
    private Date dataRealizacji;

    @Column(name = "cena")
    private double cena;

    @Column(name = "ilosc")
    private Long ilosc;


    @ManyToOne
    @JoinColumn(name = "oferta_zakupu_id")
    private OfertaZakupu ofertaZakupu;

    @ManyToOne
    @JoinColumn(name = "oferta_sprzedazy_id")
    private OfertaSprzedazy ofertaSprzedazy ;


    public Date getDataRealizacji() {
        return dataRealizacji;
    }

    public void setDataRealizacji(Date dataRealizacji) {
        this.dataRealizacji = dataRealizacji;
    }

    public Long getIlosc() {
        return ilosc;
    }

    public void setIlosc(Long ilosc) {
        this.ilosc = ilosc;
    }

    public OfertaZakupu getOfertaZakupu() {
        return ofertaZakupu;
    }

    public void setOfertaZakupu(OfertaZakupu ofertaZakupu) {
        this.ofertaZakupu = ofertaZakupu;
    }

    public OfertaSprzedazy getOfertaSprzedazy() {
        return ofertaSprzedazy;
    }

    public void setOfertaSprzedazy(OfertaSprzedazy ofertaSprzedazy) {
        this.ofertaSprzedazy = ofertaSprzedazy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZrealizowaneZlecenia that = (ZrealizowaneZlecenia) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public String toString() {
        return "ZrealizowaneZlecenia{" +
            "id=" + id +
            ", dataRealizacji=" + dataRealizacji +
            ", cena=" + cena +
            ", ilosc=" + ilosc +
            ", ofertaZakupu=" + ofertaZakupu +
            ", ofertaSprzedazy=" + ofertaSprzedazy +
            '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }


}
