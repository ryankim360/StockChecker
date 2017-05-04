package edu.temple.stockchecker;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class QuoteService extends IntentService {

    String stockName;
    String stockPrice;

    public QuoteService() {
        super("QuoteService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        getQuote(intent.getStringExtra("stock_symbol"));
    }

    public void getQuote(final String symbol) {
        URL url;
        Stocks stocks = new Stocks();

        try {
            url = new URL ("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json/?symbol=" + symbol);
            URLConnection urlc = url.openConnection();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String line;
            while((line = bfr.readLine()) != null) {
                JSONObject jsonObject = new JSONObject(line);
                for (int i = 0; i <jsonObject.length(); i++) {
                    stockName = jsonObject.getString("Name");
                    stockPrice = jsonObject.getString("LastPrice");
                }
            }
            stocks.stockNames.add(stockName);
            stocks.stockPrices.add(stockPrice);
            Log.d ("StockName", stocks.stockNames.get(0));
            Log.d ("StockPrice", stocks.stockPrices.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
