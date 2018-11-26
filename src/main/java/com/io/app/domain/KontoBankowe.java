package com.io.app.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "konto_bankowe")
public class KontoBankowe {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min = 26, max = 26)
    @Column(name="numer",length = 26)
    private String numer;
    @Column(name = "stan_konta")
    private double stan_konta;
    @Size(max = 50)
    @Column(name = "waluta",length =50 )
    private String waluta;

    @ManyToOne
    private User user;


    //Constructor
    public KontoBankowe() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KontoBankowe that = (KontoBankowe) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }


    @Override
    public String toString() {
        return "KontoBankowe{" +
            "id=" + id +
            ", numer='" + numer + '\'' +
            ", stan_konta=" + stan_konta +
            ", waluta='" + waluta + '\'' +
            ", user=" + user +
            '}';
    }
//Getters&Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public double getStan_konta() {
        return stan_konta;
    }

    public void setStan_konta(double stan_konta) {
        this.stan_konta = stan_konta;
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


}
