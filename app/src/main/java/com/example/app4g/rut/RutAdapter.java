package com.example.app4g.rut;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.app4g.R;
import com.example.app4g.Utils.Utils;
import com.example.app4g.rut.model.Item;
import com.example.app4g.rut.model.Rut;
import com.example.app4g.server.AppController;

import java.util.List;


public class RutAdapter extends RecyclerView.Adapter<RutAdapter.ViewHolder> {
    private List<Item> ruts;
    private RutAdapter.OnItemSelected listener;
    private RutAdapter.onCartSelected cartListiner;
    private String matkul ;
    Activity context ;

    public interface OnItemSelected {
        void onSelect(Rut rut);
    }

    public interface onCartSelected {
        void onCartSelect(Item rut);
    }

    public RutAdapter(List<Item> data, Activity context, onCartSelected cartListiner) {
        this.ruts = data;
        this.context = context ;
        this.cartListiner = cartListiner ;
    }


    @Override
    public RutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        RutAdapter.ViewHolder viewHolder = new RutAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RutAdapter.ViewHolder holder, final int position) {
        final Item rut = ruts.get(position);
        //holder.mkdmatkul.setText(laporan.get);

        holder.mHarga.setText(Utils.convertRupiah(String.valueOf(rut.getHarga())));
        holder.mNama.setText(rut.getNamaItem());
        holder.mToko.setText(rut.getDistributor().getNama());
        holder.mStok.setText(String.valueOf(rut.getStok()));
        //holder.itemView.setOnClickListener(view -> listener.onSelect(rut));
        if(!rut.getFoto().equals(""))
            Glide.with(context)
                    .load(AppController.getApplication().getResources().getString(R.string.img_end_point)+rut.getFoto())
                    .apply(new RequestOptions().placeholder(R.mipmap.loading_image))
                    .into(holder.mIconImage);
        else Glide.with(context)
                .load(R.drawable.shopping_bag)
                .apply(new RequestOptions().placeholder(R.mipmap.loading_image))
                .into(holder.mIconImage);
        holder.mCart.setOnClickListener(view -> cartListiner.onCartSelect(rut));
    }


    @Override
    public int getItemCount() {
        return ruts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNama, mHarga , mStok , mToko;
        LinearLayout mCart ;
        ImageView mIconImage;

        ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mCart = view.findViewById(R.id.mCart);
            mStok = view.findViewById(R.id.mStok);
            mIconImage = view.findViewById(R.id.icon_image);
            mHarga = view.findViewById(R.id.mHarga);
            mToko = view.findViewById(R.id.mToko);
//            mTime = view.findViewById(R.id.mTime);
        }
    }



}
