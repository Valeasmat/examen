package com.everis.examen.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {
    private Long id;
    private Integer productId;
    private Integer warehouseId;
    private Integer quantity;
}
