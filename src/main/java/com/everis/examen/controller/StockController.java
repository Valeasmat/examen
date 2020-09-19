package com.everis.examen.controller;

import com.everis.examen.dto.StockDto;
import com.everis.examen.dto.StockQuantityByProductIdResponseDto;
import com.everis.examen.dto.StockSaveRequestDto;
import com.everis.examen.entity.Stock;
import com.everis.examen.exception.ResourceNotFoundException;
import com.everis.examen.exception.SaveErrorException;
import com.everis.examen.mapper.StockMapper;
import com.everis.examen.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("stock/{productId}")
    public StockQuantityByProductIdResponseDto getQuantityByProductId(@PathVariable("productId") Integer productId) throws ResourceNotFoundException {
        Integer quantity = stockService.quantityByProductId(productId);

        StockQuantityByProductIdResponseDto response=new StockQuantityByProductIdResponseDto();
        response.setProductId(productId);
        response.setTotal(quantity);
        return response;
    }

    @PostMapping("stock/")
    @ResponseStatus(HttpStatus.CREATED)
    public List<StockDto> save(@RequestBody List<StockSaveRequestDto> stockSaveList) throws SaveErrorException {

        List<Stock> listToSave= StockMapper.INSTANCE.toEntity(stockSaveList);
        return StockMapper.INSTANCE.map(stockService.saveList(listToSave));
    }
}
