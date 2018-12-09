package com.io.app.repository;

import com.io.app.domain.KontoBankowe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KontoBankoweRepository extends JpaRepository<KontoBankowe, Long>{

    @Query(value = "SELECT * FROM konto_bankowe WHERE jhi_user_id= ?1  ",
        countQuery = "SELECT count(*) FROM FROM konto_bankowe WHERE jhi_user_id= ?1  ",
        nativeQuery = true)
    List<KontoBankowe> findKontoBankowe(Long id);


}
