-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-11-20 12:50:54.858

-- tables
-- Table: dane_korespondencyjne
CREATE TABLE dane_korespondencyjne (
    id int  NOT NULL,
    ulica varchar(100)  NOT NULL,
    miejscowosc varchar(100)  NOT NULL,
    kod_pocztowy varchar(100)  NOT NULL,
    email varchar(100)  NOT NULL,
    telefon varchar(100)  NOT NULL,
    CONSTRAINT dane_korespondencyjne_pk PRIMARY KEY (id)
);

-- Table: jhi_user


-- Table: konto_bankowe
CREATE TABLE konto_bankowe (
    id int  NOT NULL,
    numer int  NOT NULL,
    stan_konta real  NOT NULL,
    waluta varchar(50)  NOT NULL,
    jhi_user_id int  NOT NULL,
    CONSTRAINT konto_bankowe_pk PRIMARY KEY (id)
);

-- Table: oferta_sprzedazy
CREATE TABLE oferta_sprzedazy (
    id int  NOT NULL,
    cena real  NOT NULL,
    data_wystawienia timestamp  NOT NULL,
    waluta varchar(50)  NOT NULL,
    jhi_user_id int  NOT NULL,
    CONSTRAINT oferta_sprzedazy_pk PRIMARY KEY (id)
);

-- Table: oferta_zakupu
CREATE TABLE oferta_zakupu (
    id int  NOT NULL,
    cena real  NOT NULL,
    data_wystawienia timestamp  NOT NULL,
    waluta varchar(50)  NOT NULL,
    jhi_user_id int  NOT NULL,
    CONSTRAINT oferta_zakupu_pk PRIMARY KEY (id)
);

-- Table: przelewy
CREATE TABLE przelewy (
    id int  NOT NULL,
    data timestamp  NOT NULL,
    wartosc real  NOT NULL,
    waluta varchar(50)  NOT NULL,
    na_konto_bankowe_id int  NOT NULL,
    z_konto_bankowe_id int  NOT NULL,
    CONSTRAINT przelewy_pk PRIMARY KEY (id)
);

-- Table: zrealizowane_zlecenia
CREATE TABLE zrealizowane_zlecenia (
    id int  NOT NULL,
    oferta_sprzedazy_id int  NOT NULL,
    data_realizacji timestamp  NOT NULL,
    cena real  NOT NULL,
    oferta_zakupu_id int  NOT NULL,
    CONSTRAINT zrealizowane_zlecenia_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: dane_korespondencyjne_jhi_user (table: dane_korespondencyjne)

ALTER TABLE jhi_user add COLUMN
        dane_korespondencyjne_id int ;

ALTER TABLE jhi_user ADD CONSTRAINT jhi_user_dane_korespondencyjne
    FOREIGN KEY (dane_korespondencyjne_id)
    REFERENCES dane_korespondencyjne (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;



-- Reference: konto_bankowe_jhi_user (table: konto_bankowe)
ALTER TABLE konto_bankowe ADD CONSTRAINT konto_bankowe_jhi_user
    FOREIGN KEY (jhi_user_id)
    REFERENCES jhi_user (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: oferta_sprzedazy_jhi_user (table: oferta_sprzedazy)
ALTER TABLE oferta_sprzedazy ADD CONSTRAINT oferta_sprzedazy_jhi_user
    FOREIGN KEY (jhi_user_id)
    REFERENCES jhi_user (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: oferta_zakupu_jhi_user (table: oferta_zakupu)
ALTER TABLE oferta_zakupu ADD CONSTRAINT oferta_zakupu_jhi_user
    FOREIGN KEY (jhi_user_id)
    REFERENCES jhi_user (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: przelewy_konto_bankowe_na (table: przelewy)
ALTER TABLE przelewy ADD CONSTRAINT przelewy_konto_bankowe_na
    FOREIGN KEY (na_konto_bankowe_id)
    REFERENCES konto_bankowe (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: przelewy_konto_bankowe_z (table: przelewy)
ALTER TABLE przelewy ADD CONSTRAINT przelewy_konto_bankowe_z
    FOREIGN KEY (z_konto_bankowe_id)
    REFERENCES konto_bankowe (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: zrealizowane_zlecenia_oferta_sprzedazy (table: zrealizowane_zlecenia)
ALTER TABLE zrealizowane_zlecenia ADD CONSTRAINT zrealizowane_zlecenia_oferta_sprzedazy
    FOREIGN KEY (oferta_sprzedazy_id)
    REFERENCES oferta_sprzedazy (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: zrealizowane_zlecenia_oferta_zakupu (table: zrealizowane_zlecenia)
ALTER TABLE zrealizowane_zlecenia ADD CONSTRAINT zrealizowane_zlecenia_oferta_zakupu
    FOREIGN KEY (oferta_zakupu_id)
    REFERENCES oferta_zakupu (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- End of file.

