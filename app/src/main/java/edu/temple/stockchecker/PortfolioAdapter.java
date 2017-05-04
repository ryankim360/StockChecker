package edu.temple.stockchecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ryankim on 4/25/17.
 */

public class PortfolioAdapter extends BaseAdapter {

    final Context c;
    ArrayList<String> nameList;

    public PortfolioAdapter (Context c, ArrayList<String> strings){
        this.c = c;
        this.nameList = strings;
    }

    public void add (String name) {
        nameList.add(name);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout ll = new LinearLayout(c);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView nameView = new TextView(c);
        nameView.setText(nameList.get(position));
        nameView.setTextSize(25);

        ll.addView(nameView);
        notifyDataSetChanged();
        return ll;
    }
}
