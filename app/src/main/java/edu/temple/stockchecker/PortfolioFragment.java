package edu.temple.stockchecker;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PortfolioFragment extends Fragment {

    onStockClickListener mCallback;
    Stocks stocks = new Stocks();
    ListView listView;
    String stockName;



    public PortfolioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_portfolio, container, false);
        listView = (ListView) layout.findViewById(R.id.list_view);

        PortfolioAdapter myAdapter = new PortfolioAdapter(getContext(), stocks.lst);
        listView.setAdapter(myAdapter);
        if (stockName != "geo012") {
            myAdapter.add(stockName);
        }

        myAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback = (onStockClickListener) getActivity();
                mCallback.onStockSelected(position);
            }
        });
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        stockName = "geo012";
    }


    public interface onStockClickListener {
        public void onStockSelected(int position);
    }

    public void sendString (String name) {
        stockName = name;
        //stocks.lst.add(name);
        //myAdapter.notifyDataSetChanged();
    }
}
