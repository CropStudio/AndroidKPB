package com.app.kpb2.features.rut.detailRut.kalenderTanam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.rut.model.JadwalUsahaTani;

import java.util.List;

public class KalenderTanamAdapter extends RecyclerView.Adapter<KalenderTanamAdapter.ViewHolder> {

    private final List<JadwalUsahaTani.Jadwal> jadwals;

    public KalenderTanamAdapter(List<JadwalUsahaTani.Jadwal> items) {
        jadwals = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_jadwalusahatani, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final JadwalUsahaTani.Jadwal jadwal = jadwals.get(position);
        holder.mNama.setText(jadwal.getNamaJadwal());
        holder.mTgl.setText(Utils.convertMongoDateWithoutTIme(jadwal.getTanggal()));


    }

    @Override
    public int getItemCount() {
        return jadwals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mNama, mTgl;

        public ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mTgl = view.findViewById(R.id.mTgl);

        }

    }
}
