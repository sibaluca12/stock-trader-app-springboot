package com.codecool.stocktraderspringboot.controller;

import com.codecool.stocktraderspringboot.model.Logger;
import com.codecool.stocktraderspringboot.model.Trader;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AppController {

    @Autowired
    private Trader trader;

    @Autowired
    private Logger logger;

    @GetMapping("/{stock}/{price}")
    public String stock(@PathVariable("stock") String stock, @PathVariable("price") double price){

        try {
            boolean purchased = trader.buy(stock, price);
            if(purchased){
                logger.setMessage("Stock purchased!");

                return "Stock purchased!:) " + price + " " + stock;
            }
            else {
                logger.setMessage("Couldn't buy stock");

            }
        } catch (JSONException | IOException e) {
            logger.setMessage("There was an error while attemting to buy the stock: " + e.getMessage() );
            e.printStackTrace();
            return "There was an error while attemting to buy the stock: " + stock;
        }
        logger.log();

        return "hello";
    }

}
