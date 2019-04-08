package br.com.zup.service;

import br.com.zup.Repository.PoiRepository;
import br.com.zup.config.exception.PoiException;
import br.com.zup.entity.Poi;
import br.com.zup.request.poi.CreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PoiService {

    @Autowired
    private PoiRepository repository;

    public Page<Poi> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Poi create(CreateRequest request) throws PoiException {
        Poi poi = new Poi(request.getName(), request.getCoordinateX(), request.getCoordinateY());
        this.repository.save(poi);
        return poi;
    }

    public Page<Poi> findNearby(Pageable pageable, Integer coordinateX, Integer coordinateY) {
        Double distance = 10.0;
        return this.repository.findByDistance(pageable, coordinateX, coordinateY, distance);
    }

}
