package com.everis.examen.repository;

import com.everis.examen.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long>  {

    List<Stock> findByProductId(Integer productId);

}
