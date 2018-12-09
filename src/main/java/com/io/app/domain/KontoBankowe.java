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

    @Size( max = 26)
    @Column(name="numer",length = 26)
    private String numer;
    @Column(name = "stan_konta")
    private double stanKonta;
    @Size(max = 50)
    @Column(name = "waluta",length =50 )
    private String waluta;

    @ManyToOne
    @JoinColumn(name = "jhi_user_id")
    private User user;

    @Override
    public String toString() {
        return "KontoBankowe{" +
            "id=" + id +
            ", numer='" + numer + '\'' +
            ", stanKonta=" + stanKonta +
            ", waluta='" + waluta + '\'' +
            '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KontoBankowe that = (KontoBankowe) o;

        return id != null ? id.equals(that.id) : that.id == null;
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

    public double getStanKonta() {
        return stanKonta;
    }

    public void setStanKonta(double stanKonta) {
        this.stanKonta = stanKonta;
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
