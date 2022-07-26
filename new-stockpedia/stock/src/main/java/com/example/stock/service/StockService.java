package com.example.stock.service;

import com.example.stock.persistence.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Optional<Stock> findByStock_num() {
        Optional<Stock> stock = stockRepository.findById(9092);
        return stock;
    }

}
