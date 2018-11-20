package com.io.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "zrealizowane_zlecenia")
public class ZrealizowaneZlecenia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 100)
    @Column(name = "oferta_sprzedazy_id",length = 100)
    private Long oferta_sprzedazy_id;
    @Size(max = 100)
    @Column(name = "data_realizacji",length = 100)
    private String data_realizacji;
    @Size(max = 100)
    @Column(name = "cena",length = 100)
    private double cena;
    @Size(max = 100)
    @Column(name = "oferta_zakupu_id",length = 100)
    private Long oferta_zakupu_id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZrealizowaneZlecenia that = (ZrealizowaneZlecenia) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }


    @Override
    public String toString() {
        return "Przelewy={" +
            "id=" + id +
            ", oferta_sprzedazy_id='" + oferta_sprzedazy_id + '\'' +
            ", data_realizacji='" + data_realizacji + '\'' +
            ", cena='" + cena + '\'' +
            ", oferta_zakupu_id='" + oferta_zakupu_id + '\''+
            '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOferta_sprzedazy_id() {
        return oferta_sprzedazy_id;
    }

    public void setOferta_sprzedazy_id(Long oferta_sprzedazy_id) {
        this.oferta_sprzedazy_id = oferta_sprzedazy_id;
    }

    public String getData_realizacji() {
        return data_realizacji;
    }

    public void setData_realizacji(String data_realizacji) {
        this.data_realizacji = data_realizacji;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Long getOferta_zakupu_id() {
        return oferta_zakupu_id;
    }

    public void setOferta_zakupu_id(Long oferta_zakupu_id) {
        this.oferta_zakupu_id = oferta_zakupu_id;
    }
}
