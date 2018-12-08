package com.io.app.repository;

import com.io.app.domain.ZrealizowaneZlecenia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ZrealizowaneZleceniaRepository extends JpaRepository<ZrealizowaneZlecenia, Long> {

//    @Query(value = "SELECT * FROM zrealizowane_zlecenia WHERE oferta_sprzedazy_id = ?1 or oferta_zakupu_id = ?1" +
//        " ORDER BY ?#{#pageable}",
//        countQuery = "SELECT count(*) FROM zrealizowane_zlecenia WHERE oferta_sprzedazy_id = ?1 or oferta_zakupu_id = ?1 ",
//        nativeQuery = true)
//    Page<ZrealizowaneZlecenia> findForUeser(Long id, Pageable pageable);

    @Query(value = "select zz.id,zz.oferta_sprzedazy_id,zz.data_realizacji, zz.cena, zz.oferta_zakupu_id, zz.ilosc  " +
        "from zrealizowane_zlecenia zz join oferta_sprzedazy os on(zz.oferta_sprzedazy_id=os.id)" +
        "join oferta_zakupu oz  on(zz.oferta_zakupu_id=oz.id) where os.jhi_user_id=?1 or oz.jhi_user_id=?1 " +
        " ORDER BY ?#{#pageable}",
        countQuery = "SELECT count(*) FROM zrealizowane_zlecenia zz join oferta_sprzedazy os on(zz.oferta_sprzedazy_id=os.id)\" +\n" +
            "        \"join oferta_zakupu oz  on(zz.oferta_zakupu_id=oz.id) where os.jhi_user_id=?1 or oz.jhi_user_id=?1 ",
        nativeQuery = true)
    Page<ZrealizowaneZlecenia> findForUser(Long id, Pageable pageable);


}
