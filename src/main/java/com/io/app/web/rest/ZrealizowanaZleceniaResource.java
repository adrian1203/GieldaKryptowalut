package com.io.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.io.app.domain.ZrealizowaneZlecenia;
import com.io.app.service.CryptocService;
import com.io.app.service.UserService;
import com.io.app.service.ZrealizowaneZleceniaService;
import com.io.app.web.rest.util.PaginationUtil;
import org.bitcoinj.params.RegTestParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/zlecenia")
public class ZrealizowanaZleceniaResource {
    private final ZrealizowaneZleceniaService zrealizowaneZleceniaService;
    private final CryptocService cryptocService;
    private final UserService userService;

    public ZrealizowanaZleceniaResource(ZrealizowaneZleceniaService zrealizowaneZleceniaService, CryptocService cryptocService, UserService userService) {
        this.zrealizowaneZleceniaService = zrealizowaneZleceniaService;
        this.cryptocService = cryptocService;

        this.userService = userService;
    }

    @PostMapping("/save")
    @Timed
    public ResponseEntity<ZrealizowaneZlecenia> createOfertaSprzedarzy(@RequestParam ZrealizowaneZlecenia zrealizowaneZlecenia) {

        return ResponseEntity.ok(this.zrealizowaneZleceniaService.createZrealizowaneZlecenia(zrealizowaneZlecenia));

    }
    @GetMapping("findAll")
    public List<ZrealizowaneZlecenia> findAllOfertaSprzedazy(){
        return  this.zrealizowaneZleceniaService.findAllZrealizowaneZlecenia();
    }
    @GetMapping("/findAll-user")
    @Timed
    public ResponseEntity<List<ZrealizowaneZlecenia>> getAllForUsers(Pageable pageable) {
        final Page<ZrealizowaneZlecenia> page = zrealizowaneZleceniaService.getAllForUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/zlecenia/user");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("test-zlecenie")
    public  void testZlcenia(){
        this.zrealizowaneZleceniaService.createZrealizowaneZlecenie(1501L,1701L,2L,2.0);
    }


}
