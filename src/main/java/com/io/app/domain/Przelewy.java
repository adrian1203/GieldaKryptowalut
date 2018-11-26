package com.io.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "przelewy")
public class Przelewy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min=10,max = 10)
    @Column(name = "data", length = 10)
    private String data;
    @Column(name = "wartosc")
    private double wartosc;
    @Size(min = 1,max = 50)
    @Column(name = "waluta",length = 50)
    private String waluta;
    @Size(min = 26, max = 26)
    @Column(name="na_konto_bankowe_id",length = 26)
    private String na_konto_bankowe_id;
    @Size(min = 26, max = 26)
    @Column(name="z_konto_bankowe_id",length = 26)
    private String z_konto_bankowe_id;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Przelewy that = (Przelewy) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }


    @Override
    public String toString() {
        return "Przelewy={" +
            "id=" + id +
            ", data='" + data + '\'' +
            ", wartosc='" + wartosc + '\'' +
            ", waluta='" + waluta + '\'' +
            ", na_konto_bankowe_id='" + na_konto_bankowe_id + '\''+
            ", z_konto_bankowe_id='" + z_konto_bankowe_id + '\''+
            '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public String getNa_konto_bankowe_id() {
        return na_konto_bankowe_id;
    }

    public void setNa_konto_bankowe_id(String na_konto_bankowe_id) {
        this.na_konto_bankowe_id = na_konto_bankowe_id;
    }

    public String getZ_konto_bankowe_id() {
        return z_konto_bankowe_id;
    }

    public void setZ_konto_bankowe_id(String z_konto_bankowe_id) {
        this.z_konto_bankowe_id = z_konto_bankowe_id;
    }

}
