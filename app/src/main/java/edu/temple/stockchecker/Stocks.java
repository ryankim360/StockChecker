package edu.temple.stockchecker;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ryankim on 4/30/17.
 */

public class Stocks {

    public ArrayList<String> stockNames = new ArrayList<>();
    public ArrayList<String> stockPrices = new ArrayList<>();
    public ArrayList<String> lst = new ArrayList<>();
    int position;

    public Stocks() {
    }

    public void add (String stockName) {
        lst.add(stockName);
    }


}
