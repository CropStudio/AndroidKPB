package com.app.kpb2.features.petani.profile.komoditas;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.features.data_produksi.model.DataProduksi;
import com.app.kpb2.features.petani.profile.komoditas.model.Komoditas;
import com.app.kpb2.ui.SweetDialogs;

import java.util.List;


public class KomoditasAdapter extends RecyclerView.Adapter<KomoditasAdapter.ViewHolder> {
    public List<Komoditas> komoditass;
    private KomoditasAdapter.OnItemSelected listener;
    Activity context;


    public KomoditasAdapter(List<Komoditas> data, Activity context, OnItemSelected listener) {
        this.komoditass = data;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemSelected {
        void onHapus(Komoditas komoditas, int index);
    }


    @Override
    public KomoditasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_komoditas, parent, false);

        KomoditasAdapter.ViewHolder viewHolder = new KomoditasAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final KomoditasAdapter.ViewHolder holder, final int position) {
        final Komoditas komoditas = komoditass.get(position);
        holder.mKomoditas.setText(komoditas.getNamaKomoditas());
        holder.mBtnDelete.setOnClickListener(view -> SweetDialogs.confirmDialog(context, "Apakah Anda Yakin ?" , "ingin menghapus subsektor ini ?" , "Berhasil memuat permintaan .", string -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listener.onHapus(komoditas, position);
            }
        }));




    }


    @Override
    public int getItemCount() {
        return komoditass.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mKomoditas, mJmlah, count,mhasilProduksi,mSiklus;
        ImageView mBtnDelete;

        ViewHolder(View view) {
            super(view);
//            mSubsektor = view.findViewById(R.id.mSubsektor);
            mKomoditas = view.findViewById(R.id.mKomoditas);
            mBtnDelete = view.findViewById(R.id.mBtnDelete);

        }
    }


}
