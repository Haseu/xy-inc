package br.com.zup.controller;

import br.com.zup.config.exception.PoiException;
import br.com.zup.entity.Poi;
import br.com.zup.request.poi.CreateRequest;
import br.com.zup.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/poi")
public class PointsOfInterestController {

    @Autowired
    private PoiService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Page<Poi> findAll(Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Poi create(@Valid @RequestBody CreateRequest poi) throws PoiException {
        return service.create(poi);
    }

    @GetMapping(path = "/nearby", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private Page<Poi> gethPoisNearby(Pageable pageable, @Valid @RequestParam("x") Integer coordinateX, @Valid @RequestParam("y") Integer coordinateY) {
        return service.findNearby(pageable, coordinateX, coordinateY);
    }

}
