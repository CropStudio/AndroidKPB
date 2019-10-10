package com.example.app4g.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.animation.Positioning;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.data.DataPupuk;
import com.example.app4g.data.DataPupuk;

import java.util.List;

public class AdapterPupuk extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPupuk> item;

    public AdapterPupuk(Activity activity, List<DataPupuk> item) {
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
            convertView = inflater.inflate(R.layout.content_pupuk, null);//buat xml layout content


        TextView thnRdkk = (TextView) convertView.findViewById(R.id.thnRdkk);
        TextView nikPetani = (TextView) convertView.findViewById(R.id.nikPetani);
        TextView namaPetani = (TextView) convertView.findViewById(R.id.namaPetani);
        TextView subsektro = (TextView) convertView.findViewById(R.id.subsektor);
        TextView totPupuk = (TextView) convertView.findViewById(R.id.totPupuk);

        double urea1 = Double.parseDouble(item.get(position).getUreaMt1());
        double urea2 = Double.parseDouble(item.get(position).getUreaMt2());
        double urea3 = Double.parseDouble(item.get(position).getUreaMt3());
        double sp361 = Double.parseDouble(item.get(position).getSp36Mt1());
        double sp362 = Double.parseDouble(item.get(position).getSp36Mt2());
        double sp363 = Double.parseDouble(item.get(position).getSp36Mt3());
        double za1   = Double.parseDouble(item.get(position).getZaMt1());
        double za2   = Double.parseDouble(item.get(position).getZaMt2());
        double za3   = Double.parseDouble(item.get(position).getZaMt3());
        double npk1  = Double.parseDouble(item.get(position).getNpkMt1());
        double npk2  = Double.parseDouble(item.get(position).getNpkMt2());
        double npk3  = Double.parseDouble(item.get(position).getNpkMt3());
        double organik1  = Double.parseDouble(item.get(position).getOrganikMt1());
        double organik2  = Double.parseDouble(item.get(position).getOrganikMt2());
        double organik3  = Double.parseDouble(item.get(position).getOrganikMt3());

        double totalPupuk = urea1 + urea2 + urea3 + sp361 + sp362 + sp363 + za1 +
                             za2 + za3 + npk1 + npk2 + npk3 + organik1 + organik2 + organik3;

        totPupuk.setText(String.valueOf(totalPupuk) + " Kg");
        thnRdkk.setText(item.get(position).getTahunRdkk());
        nikPetani.setText(item.get(position).getNikPetani());
        namaPetani.setText(item.get(position).getNamaPetani());
        subsektro.setText(item.get(position).getSubsektor());
        return convertView;
    }
}
