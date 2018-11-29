package com.io.app.service;


import com.io.app.domain.ZrealizowaneZlecenia;
import com.io.app.repository.ZrealizowaneZleceniaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZrealizowaneZleceniaService {
    private final Logger log = LoggerFactory.getLogger(ZrealizowaneZlecenia.class);

    private final ZrealizowaneZleceniaRepository zrealizowaneZleceniaRepository;


    public ZrealizowaneZleceniaService(ZrealizowaneZleceniaRepository zrealizowaneZleceniaRepository) {
        this.zrealizowaneZleceniaRepository = zrealizowaneZleceniaRepository;
    }
    public ZrealizowaneZlecenia createZrealizowaneZlecenia(ZrealizowaneZlecenia zrealizowaneZlecenia  ){
        return this.zrealizowaneZleceniaRepository.save(zrealizowaneZlecenia);
    }
    public List<ZrealizowaneZlecenia> findAllZrealizowaneZlecenia(){
        return this.zrealizowaneZleceniaRepository.findAll();
    }
}
