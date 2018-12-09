package com.io.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "przelewy")
public class Przelewy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data")
    private Date data;
    @Column(name = "wartosc")
    private double wartosc;
    @Size(min = 1, max = 50)
    @Column(name = "waluta", length = 50)
    private String waluta;

    @ManyToOne
    @JoinColumn(name="na_konto_bankowe_id")
    private KontoBankowe naKontoBankowe;

    @ManyToOne
    @JoinColumn(name = "z_konto_bankowe_id")
    private KontoBankowe zKontoBankowe;

    @Override
    public String toString() {
        return "Przelewy{" +
            "id=" + id +
            ", data=" + data +
            ", wartosc=" + wartosc +
            ", waluta='" + waluta + '\'' +
            ", naKontoBankowe=" + naKontoBankowe +
            ", zKontoBankowe=" + zKontoBankowe +
            '}';
    }

    public void setData(Date data) {
        this.data = data;
    }

    public KontoBankowe getNaKontoBankowe() {
        return naKontoBankowe;
    }

    public void setNaKontoBankowe(KontoBankowe naKontoBankowe) {
        this.naKontoBankowe = naKontoBankowe;
    }

    public KontoBankowe getzKontoBankowe() {
        return zKontoBankowe;
    }

    public void setzKontoBankowe(KontoBankowe zKontoBankowe) {
        this.zKontoBankowe = zKontoBankowe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Przelewy that = (Przelewy) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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




}
