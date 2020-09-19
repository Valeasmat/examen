package com.everis.examen.mapper;

import com.everis.examen.dto.StockDto;
import com.everis.examen.dto.StockSaveRequestDto;
import com.everis.examen.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel="spring")
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    public StockDto map(Stock stock);

    public List<StockDto> map (List<Stock> stockList);

    public Stock toEntity(StockSaveRequestDto stockSaveRequestDto);

    public List<Stock> toEntity(List<StockSaveRequestDto> stockSaveRequestDto);

}
