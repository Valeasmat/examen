package com.everis.examen.service;

import com.everis.examen.entity.Stock;
import com.everis.examen.exception.ResourceNotFoundException;
import com.everis.examen.exception.SaveErrorException;

import java.util.List;

public interface StockService {

    public Integer quantityByProductId(Integer productId) throws ResourceNotFoundException;

    public List<Stock> saveList(List<Stock> stockList) throws SaveErrorException;
}
