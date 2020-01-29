package com.example.app4g.features.rut.detailRut.saprotan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.Utils.Utils;
import com.example.app4g.features.rut.model.KebutuhanSaprotan;

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
        holder.mHarga.setText(Utils.convertRupiah(kebutuhanSaprotan.getHarga()));
        holder.mJumlah.setText(kebutuhanSaprotan.getJumlah());
        holder.mHargaSubsidi.setText(Utils.convertRupiah(kebutuhanSaprotan.getHargaSubsidi()));
        holder.mJatahSubsidi.setText(kebutuhanSaprotan.getJatahSubsidi());
        holder.mJumlahNonSubsidi.setText(kebutuhanSaprotan.getJumlahNonSubsidi());
        holder.mLuasLahan.setText(kebutuhanSaprotan.getLuasLahan());
        holder.mSubTotal.setText(Utils.convertRupiah(String.valueOf(kebutuhanSaprotan.getSubTotal())));
//        holder.mJenisTanaman.setText(JenisTnm);

    }

    @Override
    public int getItemCount() {
        return kebutuhanSaprotans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mNamaPupuk, mHarga, mJumlah, mHargaSubsidi, mJatahSubsidi, mJumlahNonSubsidi, mLuasLahan, mSubTotal;

        public ViewHolder(View view) {
            super(view);
            mNamaPupuk = (TextView) view.findViewById(R.id.mNamaPupuk);
            mHarga = (TextView) view.findViewById(R.id.mHarga);
            mJumlah = (TextView) view.findViewById(R.id.mJumlah);
            mHargaSubsidi = (TextView) view.findViewById(R.id.mHargaSubsidi);
            mJatahSubsidi = (TextView) view.findViewById(R.id.mJatahSubsidi);
            mJumlahNonSubsidi = (TextView) view.findViewById(R.id.mJumlahNonSubsidi);
            mLuasLahan = (TextView) view.findViewById(R.id.mLuasLahan);
            mSubTotal = (TextView) view.findViewById(R.id.mSubTotal);
           // mJenisTanaman = (TextView) view.findViewById(R.id.mJenisTanaman);
        }

    }
}
