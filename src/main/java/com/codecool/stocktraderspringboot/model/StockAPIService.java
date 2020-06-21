package com.codecool.stocktraderspringboot.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Stock price service that gets prices from Yahoo.
 **/
@Component
public class StockAPIService {
	private String symbol;

	@Autowired
	private RemoteURLReader remoteURLReader;

	private  String apiPath = "https://financialmodelingprep.com/api/v3/quote/AAPL,FB,GOOG?apikey=17b566eaee81c6e37050fc7d3f5ed93f";

	/** Get stock price from iex and return as a double
//     *  @param symbol Stock symbol, for example "aapl"
     **/
	public double getPrice() throws IOException, JSONException {

		String url = String.format(apiPath, this.symbol);
        //RemoteURLReader remoteURLReader = new RemoteURLReader();
        remoteURLReader.setEndpoint(url);
        String result = remoteURLReader.readFromUrl();
		JSONArray jsonArray = new JSONArray(result);
		//JSONObject json = new JSONObject(result);
		System.out.println(jsonArray);
		JSONObject aapl = (JSONObject) jsonArray.get(0);
        String price = aapl.get("price").toString();
        return Double.parseDouble(price);
	}
	
	/** Buys a share of the given stock at the current price. Returns false if the purchase fails */
	public boolean buy() {
		// Stub. No need to implement this.
		return true;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}
}