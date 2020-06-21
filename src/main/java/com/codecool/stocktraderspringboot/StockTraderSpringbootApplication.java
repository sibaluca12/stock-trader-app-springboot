package com.codecool.stocktraderspringboot;

import com.codecool.stocktraderspringboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StockTraderSpringbootApplication {

    

    public static void main(String[] args) {
        SpringApplication.run(StockTraderSpringbootApplication.class, args);
    }



}
