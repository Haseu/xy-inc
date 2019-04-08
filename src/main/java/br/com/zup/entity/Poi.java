package br.com.zup.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Poi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Integer coordinateX;

    @NotNull
    private Integer coordinateY;

    public Poi(String name, Integer coordinateX, Integer coordinateY){
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
}
