package com.everis.examen.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StockQuantityByProductIdResponseDto {
    private Integer productId;
    private Integer total;
}
