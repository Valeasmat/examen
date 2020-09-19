package com.everis.examen.service;

import com.everis.examen.entity.Stock;
import com.everis.examen.exception.ResourceNotFoundException;
import com.everis.examen.exception.SaveErrorException;
import com.everis.examen.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Integer quantityByProductId(Integer productId) throws ResourceNotFoundException {

        List<Stock> result=stockRepository.findByProductId(productId);
        if(result==null || result.size()==0){
            throw new ResourceNotFoundException();
        }

        Integer totalQuantity= result.stream().mapToInt(Stock::getQuantity).reduce(0, Integer::sum);
        return totalQuantity;
    }

    @Override
    public List<Stock> saveList(List<Stock> stockList) throws SaveErrorException {
        List<Stock> savedList=new ArrayList<>();
        for (Stock s:stockList) {
            if(s.getQuantity()<=0){
                throw new SaveErrorException("Quantity of product cannot be 0");
            }
            Stock st=stockRepository.save(s);
            savedList.add(st);
        }
        return savedList;
    }
}
