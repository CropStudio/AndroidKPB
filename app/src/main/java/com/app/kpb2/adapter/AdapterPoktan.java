package com.app.kpb2.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.data.DataPoktan;

import java.util.List;

public class AdapterPoktan extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPoktan> item;

    public AdapterPoktan(Activity activity, List<DataPoktan> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_poktan, null);


        TextView nama          = convertView.findViewById(R.id.namaPoktan);
        TextView alamatPoktan  = convertView.findViewById(R.id.alamatPoktan);

        nama.setText(item.get(position).getNamaPoktan());
        alamatPoktan.setText(item.get(position).getKabupaten()+" "
                +item.get(position).getKecamatan()+" "+
                item.get(position).getDesa());

        return convertView;
    }

}
