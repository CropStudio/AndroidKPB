package com.app.app4g.features.rut.aset;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.app4g.R;
import com.app.app4g.Utils.Utils;
import com.app.app4g.features.e_commerce.model.Item;
import com.app.app4g.features.petani.profile.model.AsetPetani;
import com.app.app4g.server.App;
import com.app.app4g.ui.SweetDialogs;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;


public class AsetAdapter extends RecyclerView.Adapter<AsetAdapter.ViewHolder> {
    public List<AsetPetani> asets;
    private AsetAdapter.OnItemSelected listener;
    Activity context;


    public AsetAdapter(List<AsetPetani> data, Activity context, OnItemSelected listener) {
        this.asets = data;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemSelected {
        void onSelect(AsetPetani aset);
        void onHapus(AsetPetani aset);
    }


    @Override
    public AsetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_komoditi, parent, false);

        AsetAdapter.ViewHolder viewHolder = new AsetAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AsetAdapter.ViewHolder holder, final int position) {
        final AsetPetani aset = asets.get(position);
        String satuan = null;
        holder.mSubsektor.setText(aset.getNamaAset());
        if (aset.getNamaAset().equals("Tanaman Pangan") || aset.getNamaAset().equals("Perkebunan")
                || aset.getNamaAset().equals("Hortikultura"))
            satuan = "Hektar";
        else
            satuan = "ekor";

        holder.mKomoditas.setText(aset.getTotalAset() + " " + satuan);
        holder.countKomoditi.setText(String.valueOf(position+1));
        holder.buttonSet.setOnClickListener(view -> listener.onSelect(aset));
        holder.mBtnHapus.setOnClickListener(view -> SweetDialogs.confirmDialog(context, "Apakah Anda Yakin ?" , "Pastikan no rekening anda sudah benar!" , "Data Berhasil disimpan .", string -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listener.onHapus(aset);
            }
        }));


    }


    @Override
    public int getItemCount() {
        return asets.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mSubsektor, mKomoditas, countKomoditi;
        Button buttonSet,mBtnHapus;

        ViewHolder(View view) {
            super(view);
            mSubsektor = view.findViewById(R.id.mSubsektor);
            mKomoditas = view.findViewById(R.id.mKomoditas);
            countKomoditi = view.findViewById(R.id.countKomoditi);
            buttonSet = view.findViewById(R.id.buttonSet);
            mBtnHapus = view.findViewById(R.id.mBtnHapus);
        }
    }


}
