package edu.temple.stockchecker;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    View layout;
    Stocks stocks = new Stocks();
    int position;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_detail, container, false);
        final TextView stockNameTV = (TextView) layout.findViewById(R.id.company_name);
        final TextView stockPriceTV = (TextView) layout.findViewById(R.id.stock_price);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //stockNameTV.setText(stocks.stockNames.get(position));
               // stockPriceTV.setText(stocks.stockPrices.get(position));
            }
        }, 1500);
        return layout;
    }

    public int sendPosition(int position) {
        return position;
    }
}
