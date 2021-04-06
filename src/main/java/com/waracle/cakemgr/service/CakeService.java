package com.waracle.cakemgr.service;

import com.waracle.cakemgr.domain.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeService {

    private final CakeRepository repository;

    public CakeService(final CakeRepository repository) {
        this.repository = repository;
    }

    public void createCake(final Cake cake) {
        repository.save(cake);
    }

    public void deleteCake(final Integer cakeId) {
        repository.deleteById(cakeId);
    }

    public List<Cake> getAvailableCakes() {
        return repository.findAll();
    }
}
