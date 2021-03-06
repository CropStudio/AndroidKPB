package com.app.kpb2.features.rutDetail;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.e_commerce.model.Item;
import com.app.kpb2.features.e_commerce.model.Product;

import java.util.List;


public class RutDetailAdapter extends RecyclerView.Adapter<RutDetailAdapter.ViewHolder> {
    private List<Item> ruts;
    private OnItemSelected listener;
    private String matkul ;
    Activity context ;
    public interface OnItemSelected {
        void onSelect(Product product);
    }

    public RutDetailAdapter(List<Item> data, Activity context) {
        this.ruts = data;
        this.context = context ;
    }


    @Override
    public RutDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        RutDetailAdapter.ViewHolder viewHolder = new RutDetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RutDetailAdapter.ViewHolder holder, final int position) {
        final Item rut = ruts.get(position);
        //holder.mkdmatkul.setText(laporan.get);

        holder.mTime.setText(Utils.convertRupiah(String.valueOf(rut.getHarga())));
        holder.mSkema.setText(rut.getNamaItem());
        //holder.itemView.setOnClickListener(view -> listener.onSelect(rut));
    }


    @Override
    public int getItemCount() {
        return ruts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mSkema, mTime;
        ImageView mIconImage;

        ViewHolder(View view) {
            super(view);
            mSkema = view.findViewById(R.id.mSkema);
            mIconImage = view.findViewById(R.id.icon_image);
            mTime = view.findViewById(R.id.mTime);
//            mTime = view.findViewById(R.id.mTime);
        }
    }


}
