package br.com.zup.Repository;

import br.com.zup.entity.Poi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PoiRepository extends JpaRepository<Poi, Long> {

    @Query("select p from Poi p where sqrt(power(p.coordinateX - :coordinateX, 2) + power(p.coordinateY - :coordinateY, 2)) < :distance")
    Page<Poi> findByDistance(Pageable pageable, @Param("coordinateX") Integer coordinateX, @Param("coordinateY") Integer coordinateY, @Param("distance") Double distance);

}
