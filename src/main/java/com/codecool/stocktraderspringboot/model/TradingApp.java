package com.codecool.stocktraderspringboot.model;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides a command line interface for stock trading.
 **/
@Component
public class TradingApp {
//	public static void main(String[] args) {
//	    TradingApp app = new TradingApp();
//		Trader trader = new Trader();
//		Logger logger = new Logger();
//		RemoteURLReader remoteURLReader = new RemoteURLReader();
//	    app.start(trader, logger, remoteURLReader);
//	}
	@Autowired
	private Logger logger;

	@Autowired
	private RemoteURLReader remoteURLReader;

	@Autowired
	private Trader trader;

	public void start() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a stock symbol (for example aapl):");
		String symbol = keyboard.nextLine();
		System.out.println("Enter the maximum price you are willing to pay: ");
		double price;
		//Logger logger = new Logger();
		try {
			price = keyboard.nextDouble();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Enter a number");
			return;
		}

		try {
			//Trader trader = new Trader();
			trader.setBid(price);
			trader.setSymbol(symbol);
			boolean purchased = trader.buy(symbol, price);

			if (purchased) {
//				Logger.getInstance().log();
				logger.setMessage("Purchased stock!");
			}
			else {
//				Logger.getInstance().log();
				logger.setMessage("Couldn't buy the stock at that price.");
			}
		} catch (Exception e) {
//			Logger.getInstance().log();
			logger.setMessage("There was an error while attempting to buy the stock: " + e.getMessage());

		}
		logger.log();
	}
}