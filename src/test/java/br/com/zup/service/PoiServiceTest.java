package br.com.zup.service;

import br.com.zup.Repository.PoiRepository;
import br.com.zup.ZupApplicationTests;
import br.com.zup.config.exception.PoiException;
import br.com.zup.entity.Poi;
import br.com.zup.request.poi.CreateRequest;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public class PoiServiceTest extends ZupApplicationTests {

    @Autowired
    private PoiService service;

    @Autowired
    private PoiRepository repository;

    @Test
    public void findAll() {
        Page<Poi> pois = service.findAll(PageRequest.of(0, 10));
        Assertions.assertThat(pois.getTotalElements()).isEqualTo(repository.count());
    }

    @Test
    public void create() throws PoiException {
        CreateRequest request = new CreateRequest("Autódromo", 21, 13);
        Poi poiPersisted = service.create(request);

        Assertions.assertThat(poiPersisted).isNotNull();
        Assertions.assertThat(poiPersisted.getId()).isNotNull();

        Optional<Poi> poi = repository.findById(poiPersisted.getId());
        Assertions.assertThat(poi.get().getName()).isEqualTo(poiPersisted.getName());
        Assertions.assertThat(poi.get().getCoordinateX()).isEqualTo(request.getCoordinateX());
        Assertions.assertThat(poi.get().getCoordinateY()).isEqualTo(request.getCoordinateY());
    }

    @Test
    public void findNearby() {
        Page<Poi> poiPage = service.findNearby(PageRequest.of(0, 10), 20, 10);
        List<Poi> content = poiPage.getContent();

        Assertions.assertThat(poiPage.getTotalElements()).isEqualTo(4);
        Optional<Poi> result = content.stream().filter(poi -> poi.getId().equals(2L)).findFirst();
        Assert.assertNotNull(result);
    }
}