

alter TABLE  zrealizowane_zlecenia add COLUMN
ilosc int;

alter table oferta_sprzedazy add COLUMN
ilosc int;
alter table oferta_sprzedazy add COLUMN
pozostala_ilosc int;

alter table oferta_zakupu add COLUMN
ilosc int;
alter table oferta_zakupu add COLUMN
pozostala_ilosc int;
