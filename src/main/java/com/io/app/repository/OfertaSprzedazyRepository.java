package com.io.app.repository;

import com.io.app.domain.OfertaSprzedazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfertaSprzedazyRepository extends JpaRepository<OfertaSprzedazy, Long> {

    @Query(value = "SELECT * FROM oferta_sprzedazy WHERE pozostala_ilosc >0 ORDER BY ?#{#pageable}",
        countQuery = "SELECT count(*) FROM oferta_sprzedarzy WHERE pozostala_ilosc = 0 ",
        nativeQuery = true)
    Page<OfertaSprzedazy> findActual(Pageable pageable);


    @Query(value = "SELECT * FROM oferta_sprzedazy WHERE jhi_user_id = ?1 ORDER BY ?#{#pageable}",
        countQuery = "SELECT count(*) FROM oferta_sprzedarzy WHERE jhi_user_id = ?1 ",
        nativeQuery = true)
    Page<OfertaSprzedazy> findForUser(Long id,Pageable pageable);

    @Query(value = "SELECT * FROM oferta_sprzedazy WHERE typ_zlecenia='NORMAL' and pozostala_ilosc >0 ORDER BY cena ",
        countQuery = "SELECT count(*) FROM oferta_sprzedazy WHERE typ_zlecenia='NORMAL' and pozostala_ilosc >0 ORDER BY cena ",
        nativeQuery = true)
    List<OfertaSprzedazy> findOferySprzedazyLIMIT();

    @Query(value = "SELECT * FROM oferta_sprzedazy WHERE typ_zlecenia='PKC' and pozostala_ilosc >0 ORDER BY datawystawienia ",
        countQuery = "SELECT count(*) FROM oferta_sprzedazy WHERE typ_zlecenia='PKC' and pozostala_ilosc >0 ORDER BY datawystawienia ",
        nativeQuery = true)
    List<OfertaSprzedazy> findOferySprzedazyPKC();

    @Query(value = "select cast(datawystawienia as VARCHAR(11)) as days , count(*)  from oferta_sprzedazy  group by days order by days ",
        nativeQuery = true)
    List<Object> getOferaSprzedazyChart();




}

