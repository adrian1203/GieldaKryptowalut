package com.io.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "oferta_sprzedazy")
public class OfertaSprzedazy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min = 1,max = 8)
    @Column(name = "cena",length = 8)
    private double cena;
    @Size(min=10,max = 10)
    @Column(name = "data_wystawienia", length = 10)
    private String data_wystawienia;
    @Size(min = 1,max = 50)
    @Column(name = "waluta",length = 50)
    private String waluta;
    @Size(max = 100)
    @Column(name = "jhi_user_id",length = 100)
    private int jhi_user_id;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        OfertaSprzedazy that = (OfertaSprzedazy)obj;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public String toString() {
        return "OfertaSprzedazy={" +
            "id=" + id +
            ", cena='" + cena + '\'' +
            ", data_wystawienia='" + data_wystawienia + '\'' +
            ", waluta='" + waluta + '\'' +
            ", jhi_user_id='" + jhi_user_id + '\''+
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

    public String getData_wystawienia() {
        return data_wystawienia;
    }

    public void setData_wystawienia(String data_wystawienia) {
        this.data_wystawienia = data_wystawienia;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public int getJhi_user_id() {
        return jhi_user_id;
    }

    public void setJhi_user_id(int jhi_user_id) {
        this.jhi_user_id = jhi_user_id;
    }
}
