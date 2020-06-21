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
	private String symbol;
	private double bid;
	private static Trader instance;

	@Autowired
	private Logger logger;

	@Autowired
	private RemoteURLReader remoteURLReader;

	@Autowired
	private StockAPIService stockService;

	public Trader() {

		this.stockService = new StockAPIService();
    }

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

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}
}