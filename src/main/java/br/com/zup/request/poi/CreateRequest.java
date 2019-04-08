package br.com.zup.request.poi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
public class CreateRequest {

    private String name;

    @Min(value=0, message = "somente inteiros positivos na cordenada x" )
    private Integer coordinateX;

    @Min(value=0, message = "somente inteiros positivos na cordenada y" )
    private Integer coordinateY;

}
