package com.codecool.stocktraderspringboot.model;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Business logic for stock trading
 **/
@Component
public class Trader {


	@Autowired
	private Logger logger;


	@Autowired
	private StockAPIService stockService;



	/** Checks the price of a stock, and buys it if the price is not greater than the bid amount.
	 * 	@return whether any stock was bought */
	public boolean buy(String symbol, double bid) throws IOException, JSONException {
//		stockService.setSymbol(this.symbol);
		double price = stockService.getPrice();
        boolean result;
        //Logger logger = new Logger();
		if (price <= bid) {
			result = true;
			stockService.buy();

//			Logger.getInstance().log();
			logger.setMessage("Purchased " + symbol + " stock at $" + bid + ", since its higher that the current price ($" + price + ")");
		}
		else {
			logger.setMessage("Bid for " + symbol + " was $" + bid + " but the stock price is $" + price + ", no purchase was made.");
//            Logger.getInstance().log();
			result = false;
		}
		logger.log();
		return result;
	}

}