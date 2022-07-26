package com.example.stock.service;

import com.example.stock.persistence.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private StockRepository stockRepository;

    public Stock findByStock_num() {
        Stock stock = stockRepository.findByStock_num(9190);
        return stock;
    }

}
