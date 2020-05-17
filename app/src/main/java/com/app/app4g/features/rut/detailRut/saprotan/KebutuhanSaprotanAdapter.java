package com.app.app4g.features.rut.detailRut.saprotan;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.app4g.R;
import com.app.app4g.Utils.Utils;
import com.app.app4g.features.rut.model.KebutuhanSaprotan;
import com.google.gson.Gson;

import java.util.List;

public class KebutuhanSaprotanAdapter extends RecyclerView.Adapter<KebutuhanSaprotanAdapter.ViewHolder> {

    private final List<KebutuhanSaprotan> kebutuhanSaprotans;
    String JenisTnm;

    public KebutuhanSaprotanAdapter(List<KebutuhanSaprotan> items) {
        kebutuhanSaprotans = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kebutuhan_saprotan, parent, false);
        return new ViewHolder(view);
    }

    public void swap(List<KebutuhanSaprotan> datas, String JenisTanaman) {
        if (datas == null || datas.size() == 0)
            return;
        if (kebutuhanSaprotans != null && kebutuhanSaprotans.size() > 0)
            kebutuhanSaprotans.clear();
        kebutuhanSaprotans.addAll(datas);
        if (this.JenisTnm != JenisTanaman)
            this.JenisTnm = JenisTanaman;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final KebutuhanSaprotan kebutuhanSaprotan = kebutuhanSaprotans.get(position);
        holder.mNamaPupuk.setText(kebutuhanSaprotan.getNama());
        if (kebutuhanSaprotan.getSelected() != null) {
            System.out.println(new Gson().toJson(kebutuhanSaprotan.getSelected().getHarga()));
            holder.mNamaBarang.setText(kebutuhanSaprotan.getSelected().getNamaBarang());
            holder.mHargaNormal.setText(Utils.convertRupiah(String.valueOf(kebutuhanSaprotan.getSelected().getHarga())));
            holder.mHargaSubsidi.setText(Utils.convertRupiah(String.valueOf(kebutuhanSaprotan.getSelected().getHargaSubsidi())));
//            holder.mSubTotal.setText(Utils.convertRupiah(String.valueOf(kebutuhanSaprotan.getSubTotal())));
        }
        holder.mJumlah.setText(String.valueOf(kebutuhanSaprotan.getJumlah()));
        if (kebutuhanSaprotan.getSubsidi())
            holder.mStatusSubsidi.setText("Subsidi");
        else
            holder.mStatusSubsidi.setText("Tidak Subsidi");


    }

    @Override
    public int getItemCount() {
        return kebutuhanSaprotans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mNamaPupuk, mNamaBarang, mHargaNormal, mJumlah, mHargaSubsidi, mSubTotal, mStatusSubsidi,mSubtotal;

        public ViewHolder(View view) {
            super(view);
            mNamaPupuk = view.findViewById(R.id.mNamaPupuk);
            mSubtotal = view.findViewById(R.id.mSubtotal);
            mNamaBarang = view.findViewById(R.id.mNamaBarang);
            mHargaNormal = view.findViewById(R.id.mHargaNormal);
            mJumlah = view.findViewById(R.id.mJumlah);
            mHargaSubsidi = view.findViewById(R.id.mHargaSubsidi);
            mStatusSubsidi = view.findViewById(R.id.mStatusSubsidi);
            mSubTotal = view.findViewById(R.id.mSubTotal);
        }

    }
}
