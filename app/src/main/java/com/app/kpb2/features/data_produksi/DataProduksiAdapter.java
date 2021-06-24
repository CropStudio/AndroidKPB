package com.app.kpb2.features.data_produksi;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.features.data_produksi.model.DataProduksi;
import com.app.kpb2.features.petani.profile.model.AsetPetani;
import com.app.kpb2.ui.SweetDialogs;

import java.util.List;


public class DataProduksiAdapter extends RecyclerView.Adapter<DataProduksiAdapter.ViewHolder> {
    public List<DataProduksi> dataProduksis;
    private DataProduksiAdapter.OnItemSelected listener;
    Activity context;


    public DataProduksiAdapter(List<DataProduksi> data, Activity context, OnItemSelected listener) {
        this.dataProduksis = data;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemSelected {
        void onEdit(DataProduksi dataProduksi , int index);
        void onHapus(DataProduksi dataProduksi, int index);
    }


    @Override
    public DataProduksiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_produksi, parent, false);

        DataProduksiAdapter.ViewHolder viewHolder = new DataProduksiAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DataProduksiAdapter.ViewHolder holder, final int position) {
        final DataProduksi dataProduksi = dataProduksis.get(position);
        String satuan = null;
        holder.mKomoditas.setText(dataProduksi.getKomoditas());
        holder.mhasilProduksi.setText(dataProduksi.getHasilProduksi());
        holder.mJmlah.setText(dataProduksi.getJumlahProduksi() + " "+ dataProduksi.getSatuan());
        holder.count.setText(String.valueOf(position+1));
        holder.mSiklus.setText(dataProduksi.getSiklus());
//        if (aset.getNamaAset().equals("Tanaman Pangan") || aset.getNamaAset().equals("Perkebunan")
//                || aset.getNamaAset().equals("Hortikultura"))
//            satuan = "Hektar";
//        else
//            satuan = "ekor";

//        holder.mKomoditas.setText(aset.getTotalAset() + " " + satuan);
//        holder.countKomoditi.setText(String.valueOf(position+1));
        holder.mBtnEdit.setOnClickListener(view -> listener.onEdit(dataProduksi , position));
        holder.mBtnHapus.setOnClickListener(view -> SweetDialogs.confirmDialog(context, "Apakah Anda Yakin ?" , "ingin menghapus subsektor ini ?" , "Berhasil memuat permintaan .", string -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listener.onHapus(dataProduksi, position);
            }
        }));




    }


    @Override
    public int getItemCount() {
        return dataProduksis.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mKomoditas, mJmlah, count,mhasilProduksi,mSiklus;
        Button mBtnEdit,mBtnHapus;

        ViewHolder(View view) {
            super(view);
//            mSubsektor = view.findViewById(R.id.mSubsektor);
            mKomoditas = view.findViewById(R.id.mKomoditas);
            count = view.findViewById(R.id.mCount);
            mSiklus = view.findViewById(R.id.mSiklus);
            mJmlah = view.findViewById(R.id.mJmlah);
            mhasilProduksi = view.findViewById(R.id.mhasilProduksi);
            mBtnEdit = view.findViewById(R.id.mBtnEdit);
            mBtnHapus = view.findViewById(R.id.mBtnHapus);
        }
    }


}
