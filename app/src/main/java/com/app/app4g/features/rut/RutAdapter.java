package com.app.app4g.features.rut;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.app.app4g.R;
import com.app.app4g.features.rut.model.BiayaTanam;
import com.app.app4g.features.rut.model.EstimasiPanen;
import com.app.app4g.features.rut.model.HasilPascaPanen;
import com.app.app4g.features.rut.model.JadwalUsahaTani;
import com.app.app4g.features.rut.model.KebutuhanSaprotan;
import com.app.app4g.features.rut.model.Result;
import com.app.app4g.features.rut.model.Rut;

import java.util.List;

public class RutAdapter extends RecyclerView.Adapter<RutAdapter.ViewHolder> {
    public List<Result> ruts;
//    private List<Rut> filterList;;
    private RutAdapter.onRutSelected rutListener;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;


    public interface onRutSelected {
        void onDetailData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, List<JadwalUsahaTani> jadwalUsahaTanis );
//        void onCheckBox(int position);
        void onSetuju(Rut rut);
        void onEditRut(Result rut);
    }

    public RutAdapter(List<Result> data, Activity context, onRutSelected rutListener) {
        this.ruts = data;
        this.context = context;
        this.rutListener = rutListener;
    }


    @Override
    public RutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_rut, parent, false);

        RutAdapter.ViewHolder viewHolder = new RutAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RutAdapter.ViewHolder holder, final int position) {
        final Result rut = ruts.get(position);
        holder.mMt.setText(rut.getMt());
        holder.mKomoditas.setText(rut.getKomoditas());
        holder.mTotalSaprotan.setText(rut.getSubTotalKebutuhanSaprotan());
        holder.mTotalPendapatan.setText(rut.getPendapatanKotor());
        holder.mTotalBudidaya.setText(rut.getSubTotalBiayaUsahaTani());
        holder.mTotalKeuntungan.setText(rut.getSubPrediksiPendapatan());
//        if(rut.getStatus().equals("")) {
//            holder.mStatus.setText("Menunggu Persetujuan Dari anda");
//            holder.mStatusBar.setBackgroundColor(Color.RED);
//        }else{
//            holder.mStatus.setText("Telah Disetujui");
//            holder.mStatusBar.setBackgroundColor(Color.GREEN);
//        }
        holder.mBtnKebutuhan.setOnClickListener(view->rutListener.onDetailData(rut.getKebutuhanSaprotan(),rut.getGarapDanPemeliharaan(),rut.getJadwalUsahaTani()));
//        holder.mTotalSaprotan.setText(Utils.convertRupiah(String.valueOf(rut.getSubTotalSaprotan())));
//        holder.mTotalBudidaya.setText(Utils.convertRupiah(String.valueOf(rut.getSubTotalGarapDanPemeliharaan())));
//        holder.mTotalPendapatan.setText(Utils.convertRupiah(String.valueOf(rut.getSubPendapatanKotor())));
//        holder.mTotalKeuntungan.setText(Utils.convertRupiah(String.valueOf(rut.getSubPrediksiPendapatan())));
        //        holder.mCheckBox.setOnCheckedChangeListener(null);
//        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    rut.setSelected(true);
//                } else {
//                    rut.setSelected(false);
//                }
////                rut.setQty(Integer.parseInt(holder.mQty.getNumber()));
//                rutListener.onCheckBox(position);
//            }
//        if(rut.getStatus().equals(""))
//            holder.mBtnSetuju.setEnabled(true);
//        else {
//            holder.mBtnSetuju.setEnabled(false);
//            holder.mBtnSetuju.setBackgroundColor(context.getResources().getColor(R.color.grey));

//        }

        holder.mBtnEdit.setOnClickListener(view->rutListener.onEditRut(rut));
    }



    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mMt, mTotalSaprotan , mTotalBudidaya, mTotalPendapatan , mTotalKeuntungan, mStatus, mToko, mSubsidi , mKomoditas;
        Button mBtnKebutuhan ,mBtnSetuju,mBtnEdit;
        View mStatusBar ;
        CheckBox mCheckBox;
        ViewHolder(View view) {
            super(view);
            mMt = view.findViewById(R.id.mMt);
//            mJenisTanaman = view.findViewById(R.id.mJenisTanaman);
//            mCheckBox = view.findViewById(R.id.mCheckbox);
            mStatus = view.findViewById(R.id.mStatus);
            mKomoditas = view.findViewById(R.id.mKomoditas);
            mTotalSaprotan = view.findViewById(R.id.mTotalSaprotan);
            mTotalBudidaya = view.findViewById(R.id.mTotalBudidaya);
            mTotalPendapatan = view.findViewById(R.id.mTotalPendapatan);
            mTotalKeuntungan = view.findViewById(R.id.mTotalKeuntungan);
            mBtnKebutuhan = view.findViewById(R.id.mBtnKebutuhan);
            mStatusBar = view.findViewById(R.id.mStatusBar);
            mBtnSetuju = view.findViewById(R.id.mBtnSetuju);
            mBtnEdit = view.findViewById(R.id.mBtnEdit);
        }

    }
}
