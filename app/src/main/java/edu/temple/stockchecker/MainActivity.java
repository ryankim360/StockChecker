package edu.temple.stockchecker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements PortfolioFragment.onStockClickListener {

    boolean twoPanes;
    Stocks stocks = new Stocks();
    String stockName;
    PortfolioFragment portfolioFragment = new PortfolioFragment();
    DetailFragment detailFragment = new DetailFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        twoPanes = (findViewById(R.id.container2) != null);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container1, new PortfolioFragment());
        ft.commit();

        if (twoPanes) {
            ft = fm.beginTransaction();
            ft.add(R.id.container2, new DetailFragment());
            ft.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mif = getMenuInflater();
        mif.inflate(R.menu.button_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_button:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_addstock, null);
                final EditText mStockName = (EditText) mView.findViewById(R.id.stockName_editText);
                Button mButton = (Button) mView.findViewById(R.id.save_button);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String stckName = mStockName.getText().toString();
                        stockName = stckName;
                        //Toast.makeText(MainActivity.this, stockName, Toast.LENGTH_SHORT).show();
                        portfolioFragment.sendString(stockName);
                        stocks.lst.add(stckName);
                        loadFragment(R.id.container1, portfolioFragment);
                    }
                });
                mBuilder.setView(mView);
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
        }
        return true;
    }

    @Override
    public void onStockSelected(int position) {
        if (!twoPanes) {
            loadFragment(R.id.container1, detailFragment);
        }
        stocks.position = position;
        Intent stockQuoteIntent = new Intent(MainActivity.this, QuoteService.class);
        stockQuoteIntent.putExtra("stock_symbol", stocks.lst.get(position));
        startService(stockQuoteIntent);
        detailFragment.sendPosition(position);
    }

    private void loadFragment (int containerId, Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerId, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
