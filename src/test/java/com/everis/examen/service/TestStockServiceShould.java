package com.everis.examen.service;

import com.everis.examen.entity.Stock;
import com.everis.examen.exception.ResourceNotFoundException;
import com.everis.examen.exception.SaveErrorException;
import com.everis.examen.repository.StockRepository;
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
public class TestStockServiceShould {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testQuantityByProductId() throws ResourceNotFoundException {
        List<Stock> stocks=new ArrayList<>();

        Stock stock1=new Stock();
        stock1.setId(1L);
        stock1.setProductId(1);
        stock1.setWarehouseId(2);
        stock1.setQuantity(1);
        Stock stock2=new Stock();
        stock2.setId(2L);
        stock2.setProductId(1);
        stock2.setWarehouseId(3);
        stock2.setQuantity(2);

        stocks.add(stock1);
        stocks.add(stock2);

        when(stockRepository.findByProductId(anyInt())).thenReturn(stocks);

        Integer result = stockService.quantityByProductId(1);

        assertEquals(3,result);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testQuantityByProductIdWhenException() throws ResourceNotFoundException {
        when(stockRepository.findByProductId(anyInt())).thenReturn(null);
        stockService.quantityByProductId(1);
    }

    @Test
    public void testSaveList() throws SaveErrorException {
        Stock stock =new Stock();
        stock.setQuantity(2);
        List<Stock> stocks=new ArrayList<>();
        stocks.add(stock);
        when(stockRepository.save(any(Stock.class))).thenReturn(new Stock());
        List<Stock> result = stockService.saveList(stocks);

        assertNotNull(result);
    }

    @Test(expected = SaveErrorException.class)
    public void testSaveListWhenException() throws SaveErrorException {
        Stock stock =new Stock();
        stock.setQuantity(0);
        List<Stock> stocks=new ArrayList<>();
        stocks.add(stock);

        stockService.saveList(stocks);
    }

}
