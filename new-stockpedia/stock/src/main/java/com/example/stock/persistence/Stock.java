package com.example.stock.persistence;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stock_num;
    private String name;
    private int value;
    private Date reg_date;

    @Override
    public String toString() {
        return stock_num + ". " + name + ": " + value + "(" + reg_date + ")";
    }
}
