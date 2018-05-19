package com.it.sonh.sqllite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sonho on 3/8/2018.
 */

public class Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<ThoiKhoaBieu> thoiKhoaBieus;

    public Adapter(Context context, ArrayList<ThoiKhoaBieu> thoiKhoaBieus) {
        this.context = context;
        this.thoiKhoaBieus = thoiKhoaBieus;
    }

    @Override
    public int getCount() {
        return thoiKhoaBieus.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_item, null);
        TextView txtName = view.findViewById(R.id.txtName);
        txtName.setText(thoiKhoaBieus.get(i).getName());
        return view;
    }
}
