package com.example.kuba.igtask;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<Market.CurrentMarkets> marketList;
    Activity activity;

    public ListViewAdapter(Activity activity, ArrayList<Market.CurrentMarkets> marketList) {
        super();
        this.activity = activity;
        this.marketList = marketList;
    }

    @Override
    public int getCount() {
        return marketList.size();
    }

    @Override
    public Object getItem(int position) {
        return marketList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mName;
        TextView mOffer;
        TextView mChange;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.mName = (TextView) convertView.findViewById(R.id.sNo);
            holder.mOffer = (TextView) convertView.findViewById(R.id.product);
            holder.mChange = (TextView) convertView.findViewById(R.id.category);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Market.CurrentMarkets item = marketList.get(position);
        holder.mName.setText(item.getInstrumentName().toString());
        holder.mOffer.setText(item.getDisplayOffer().toString());
        holder.mChange.setText(item.getNetChange().toString());

        return convertView;
    }
}