package com.example.rjhy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.rjhy.adapter_kit_text.MyActivity;

import java.util.List;

/**
 * Created by rjhy on 14-8-15.
 */
public class MyArrayAdapter extends ArrayAdapter {
    public MyArrayAdapter(Context context, int resource, List<String> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(MyActivity.TAG, "getView"+position);
        return super.getView(position, convertView, parent);
    }
}
