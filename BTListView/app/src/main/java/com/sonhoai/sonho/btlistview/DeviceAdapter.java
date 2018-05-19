package com.sonhoai.sonho.btlistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class DeviceAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<Device> devices;
    ArrayList<Device>fullList;
    public DeviceAdapter(Context context,ArrayList<Device>l){
        this.context=context;
        devices=l;
        fullList=l;
    }
    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int i) {
        return devices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=View.inflate(context,R.layout.list_item_place,null);
        }
        Device device=devices.get(i);
        ImageView imgPlace=(ImageView)view.findViewById(R.id.imgViewPlace);
        TextView tvPlaceName=(TextView)view.findViewById(R.id.tvPlaceName);
        tvPlaceName.setText(device.getDeviceName());
        if(device.getDeviceName().length()>3){
            imgPlace.setImageResource(R.drawable.earth_icon);
        }
        else  imgPlace.setImageResource(R.drawable.star_icon);

        return view;
    }

    @Override
    public Filter getFilter() {

        return null;
    }
}
