ALTER TABLE jhi_user DROP CONSTRAINT jhi_user_dane_korespondencyjne;


ALTER TABLE jhi_user
drop COLUMN dane_korespondencyjne_id;

ALTER TABLE dane_korespondencyjne add COLUMN
    jhi_user_id int;

ALTER TABLE dane_korespondencyjne ADD CONSTRAINT dane_korespondencyjne_jhi_user
    FOREIGN KEY (jhi_user_id)
    REFERENCES jhi_user (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;
