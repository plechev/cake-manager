package com.waracle.cakemgr.rest;


import com.waracle.cakemgr.service.CakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class CakeResource {

    private final CakeService service;

    public CakeResource(final CakeService service) {
        this.service = service;
    }

    @GetMapping(value = "/cakes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CakeResponse> getAllCakes() {
        return service.getAvailableCakes()
            .stream()
            .map(CakeResponse::new)
            .sorted()
            .collect(Collectors.toList());
    }

    @PostMapping(value = "/cakes", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCake(@Valid @RequestBody CakeRequest cakeRequest) { // TODO enhanced validation responses
        service.createCake(cakeRequest.assembleCake());
    }

    @DeleteMapping(value = "/cakes/{cakeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCake(@PathVariable("cakeId") Integer cakeId) {
        service.deleteCake(cakeId);
    }

}
