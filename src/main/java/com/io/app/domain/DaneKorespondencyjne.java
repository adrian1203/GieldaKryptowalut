package com.io.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dane_korespondencyjne")
public class DaneKorespondencyjne {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 100)
    @Column(name = "ulica", length = 100)
    private String ulica;
    @Size(max = 100)
    @Column(name = "miejscowosc", length = 100)
    private String miejscowosc;
    @Size(max = 100)
    @Column(name = "kod_pocztowy", length = 100)
    private String kodPocztowy;
    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;
    @Size(max = 100)
    @Column(name = "telefon", length = 100)
    private String telefon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DaneKorespondencyjne that = (DaneKorespondencyjne) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public String toString() {
        return "DaneKorespondencyjne{" +
            "id=" + id +
            ", ulica='" + ulica + '\'' +
            ", miejscowosc='" + miejscowosc + '\'' +
            ", kod_pocztowy='" + kodPocztowy + '\'' +
            ", email='" + email + '\'' +
            ", telefon='" + telefon + '\'' +
            ", user=" + user +
            '}';
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DaneKorespondencyjne() {
    }
}
