package com.io.app.service;

import ch.qos.logback.core.spi.LifeCycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ThreadService implements LifeCycle {
    private ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
    @Autowired
    CryptocService cryptocService;

    @Override
    public void start() {
    }

    @PostConstruct
    public void setup() {
        threadPool.scheduleAtFixedRate(cryptocService, 60, 120, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
