package com.io.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "oferta_zakupu")
public class OfertaZakupu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cena")
    private double cena;
    @Column(name = "ilosc")
    private Long ilosc;
    @Column(name = "pozostala_ilosc")
    private Long pozostalaIlosc;
    @Column(name = "datawystawienia", length = 10)
    private Date dataWystawienia;
    @Size(max = 50)
    @Column(name = "waluta",length = 50)
    private String waluta;
    @Size(max = 50)
    @Column(name = "typ_zlecenia",length = 50)
    private String typZlecenia;

    public void setIlosc(Long ilosc) {
        this.ilosc = ilosc;
    }

    public Long getIlosc() {
        return ilosc;
    }

    public Long getPozostalaIlosc() {
        return pozostalaIlosc;
    }

    public void setPozostalaIlosc(Long pozostalaIlosc) {
        this.pozostalaIlosc = pozostalaIlosc;
    }

    @ManyToOne
    @JoinColumn(name = "jhi_user_id")
    private User user;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        OfertaZakupu that = (OfertaZakupu)obj;

        return id != null ? id.equals(that.id) : that.id == null;
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

    @Override
    public String toString() {
        return "OfertaZakupu{" +
            "id=" + id +
            ", cena=" + cena +
            ", ilosc=" + ilosc +
            ", pozostalaIlosc=" + pozostalaIlosc +
            ", dataWystawienia=" + dataWystawienia +
            ", waluta='" + waluta + '\'' +
            ", typZlecenia='" + typZlecenia + '\'' +
            '}';
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public String getTypZlecenia() {
        return typZlecenia;
    }

    public void setTypZlecenia(String typZlecenia) {
        this.typZlecenia = typZlecenia;
    }
}
