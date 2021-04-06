package com.waracle.cakemgr.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.domain.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.Set;

@Component
public class Initialiser {

    private static final Logger log = LogManager.getLogger(Initialiser.class);

    @Autowired
    private CakeRepository repository;

    @Value("${cakes.data.location}")
    private String cakesDataLocation;

    @EventListener
    public void initialiseDatabase(ContextRefreshedEvent event) {
        try (InputStream inputStream = new URL(cakesDataLocation).openStream()) {

            ObjectMapper mapper = new ObjectMapper();
            final Set<Cake> cakes = mapper.readValue(inputStream, new TypeReference<>() {
            });

            repository.saveAll(cakes);

            log.info("Initialised cake manager with {} cakes", cakes.size());

        } catch (Exception e) {
            throw new BeanInitializationException("Failed to initialise cake data", e);
        }
    }

}
