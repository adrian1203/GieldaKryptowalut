package com.io.app.repository;

import com.io.app.domain.Przelewy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrzelewyRespository extends JpaRepository<Przelewy, Long> {

    @Query(value = "SELECT * FROM przelewy WHERE na_konto_bankowe_id = ?1 or  z_konto_bankowe_id = ?1 ORDER BY ?#{#pageable}",
        countQuery = "SELECT count(*) FROM przelewy WHERE na_konto_bankowe_id = ?1 or  z_konto_bankowe_id = ?1 ",
        nativeQuery = true)
    Page<Przelewy> findForUser(Long id, Pageable pageable);
}
