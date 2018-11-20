package com.io.app.repository;

import com.io.app.domain.DaneKorespondencyjne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaneKorespondencyjneRepository extends JpaRepository<DaneKorespondencyjne,Long>{
}
