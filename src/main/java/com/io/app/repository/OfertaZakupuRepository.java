package com.io.app.repository;

import com.io.app.domain.OfertaZakupu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfertaZakupuRepository extends JpaRepository<OfertaZakupu, Long>{

    @Query(value = "SELECT * FROM oferta_zakupu WHERE pozostala_ilosc >0 ORDER BY ?#{#pageable}",
        countQuery = "SELECT count(*) FROM oferta_zakupu WHERE pozostala_ilosc = 0 ",
        nativeQuery = true)
    Page<OfertaZakupu> findActual(Pageable pageable);


    @Query(value = "SELECT * FROM oferta_zakupu WHERE jhi_user_id = ?1 ORDER BY ?#{#pageable}",
        countQuery = "SELECT count(*) FROM oferta_zakupu WHERE jhi_user_id = ?1 ",
        nativeQuery = true)
    Page<OfertaZakupu> findForUser(Long id,Pageable pageable);

    @Query(value = "SELECT * FROM oferta_zakupu WHERE typ_zlecenia='PKC' ORDER BY datawystawienia ",
        countQuery = "SELECT count(*) FROM oferta_zakupu WHERE typ_zlecenia='PKC' ORDER BY datawystawienia ",
        nativeQuery = true)
    List<OfertaZakupu> findOferyZakupuPKC();


    @Query(value = "SELECT * FROM oferta_zakupu WHERE typ_zlecenia='NORMAL' ORDER BY cena desc ",
        countQuery = "SELECT count(*) FROM oferta_zakupu WHERE typ_zlecenia='NORMAL' ORDER BY cena desc ",
        nativeQuery = true)
    List<OfertaZakupu> findOferyZakupuLIMIT();

}
