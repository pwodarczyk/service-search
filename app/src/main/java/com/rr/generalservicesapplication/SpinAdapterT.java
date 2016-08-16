package com.rr.generalservicesapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by S A Wattoo on 3/19/2016.
 */
public class SpinAdapterT extends ArrayAdapter<Const> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<Const> values;

    public SpinAdapterT(Context context, int textViewResourceId,
                        ArrayList<Const> values) {
        super(context, textViewResourceId,values);
        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return values.size();
    }

    public Const getItem(int position){
        return values.get(position);
    }

    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.WHITE);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getName());
        label.setPadding(16, 0, 16, 0);
        label.setTextSize(16);

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getName());
        label.setPadding(16, 36, 16, 36);
        label.setTextSize(16);

        return label;
    }
}
