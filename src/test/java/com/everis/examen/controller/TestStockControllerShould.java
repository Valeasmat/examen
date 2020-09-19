package com.everis.examen.controller;

import com.everis.examen.dto.StockDto;
import com.everis.examen.dto.StockQuantityByProductIdResponseDto;
import com.everis.examen.dto.StockSaveRequestDto;
import com.everis.examen.entity.Stock;
import com.everis.examen.exception.ResourceNotFoundException;
import com.everis.examen.exception.SaveErrorException;
import com.everis.examen.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestStockControllerShould {

    @InjectMocks
    private StockController stockController;
    @Mock
    private StockService stockService;

    @Test
    public void testGetQuantityByProductId() throws ResourceNotFoundException {
        when(stockService.quantityByProductId(anyInt())).thenReturn(10);
        StockQuantityByProductIdResponseDto response=stockController.getQuantityByProductId(1);

        assertNotNull(response);
        assertEquals(10,response.getTotal());
        assertEquals(1,response.getProductId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetQuantityByProductIdWhenException() throws ResourceNotFoundException{
        when(stockService.quantityByProductId(anyInt())).thenThrow(new ResourceNotFoundException());
        stockController.getQuantityByProductId(999);
    }

    @Test
    public void testSave() throws SaveErrorException {

        List<StockSaveRequestDto> stockRequestList=new ArrayList<>();
        StockSaveRequestDto stockSaveRequestDto=new StockSaveRequestDto();
        stockSaveRequestDto.setProductId(1);
        stockSaveRequestDto.setWarehouseId(2);
        stockSaveRequestDto.setQuantity(1);
        stockRequestList.add(stockSaveRequestDto);


        List<Stock> stocks=new ArrayList<>();
        Stock stock1=new Stock();
        stock1.setId(1L);
        stock1.setProductId(1);
        stock1.setWarehouseId(2);
        stock1.setQuantity(1);
        stocks.add(stock1);

        when(stockService.saveList(any(List.class))).thenReturn(stocks);
        List<StockDto> result = stockController.save(stockRequestList);

        assertEquals(1,result.size());
        assertEquals(1L,result.get(0).getId());
        assertEquals(1,result.get(0).getProductId());
        assertEquals(2,result.get(0).getWarehouseId());
        assertEquals(1,result.get(0).getQuantity());

    }

    @Test(expected = SaveErrorException.class)
    public void testSaveWhenException() throws SaveErrorException {
        List<StockSaveRequestDto> stockRequestList=new ArrayList<>();
        StockSaveRequestDto stockSaveRequestDto=new StockSaveRequestDto();
        stockSaveRequestDto.setProductId(1);
        stockSaveRequestDto.setWarehouseId(2);
        stockSaveRequestDto.setQuantity(0);
        stockRequestList.add(stockSaveRequestDto);

        when(stockService.saveList(any(List.class))).thenThrow(new SaveErrorException("error"));
        stockController.save(stockRequestList);

    }
}
