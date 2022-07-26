package com.example.stock.controller;

import com.example.stock.persistence.Stock;
import com.example.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {
    private StockService stockService;

    @GetMapping("/{num}")
    public String getStock() {
        Stock stock = stockService.findByStock_num();
        return stock.toString();
    }
}
