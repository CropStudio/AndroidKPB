package com.app.kpb2.features.pasar_bebas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.Glide.MyGlideModule;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.e_commerce.model.Item;
import com.app.kpb2.features.e_commerce.model.Product;
import com.app.kpb2.features.pasar_bebas.model.Items;
import com.app.kpb2.network.RestService;
import com.app.kpb2.server.App;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;


public class PasarBebasAdapter extends RecyclerView.Adapter<PasarBebasAdapter.ViewHolder> {
    private List<Items> items;
    private List<Item> filterList;
    private PasarBebasAdapter.OnItemSelected listener;
    private PasarBebasAdapter.onCartSelected cartListiner;
    private String matkul;
    Activity context;

    public interface OnItemSelected {
        void onSelect(Product product);
    }

    public interface onCartSelected {
        void onItemSelect(Items item, ImageView image);
    }

    public PasarBebasAdapter(List<Items> data, Activity context, onCartSelected cartListiner) {
        this.items = data;
        this.context = context;
        this.cartListiner = cartListiner;
    }


    @Override
    public PasarBebasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        PasarBebasAdapter.ViewHolder viewHolder = new PasarBebasAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PasarBebasAdapter.ViewHolder holder, final int position) {
        final Items item = items.get(position);
//        if (rut.getKategori().equals("Subsidi"))
//            holder.mSubsidi.setVisibility(View.VISIBLE);
//        else
//            holder.mSubsidi.setVisibility(View.GONE);
//        holder.mHarga.setText(Utils.convertRupiah(String.valueOf(rut.getHarga())));
        holder.mNama.setText(item.getNamaBarang());
        holder.mHarga.setText(Utils.convertRupiah(String.valueOf(item.getHargaBarang())));
        holder.mStok.setText(String.valueOf(item.getStok()));
//        holder.mToko.setText(rut.getDistributor().getNama());
//        holder.mStok.setText(String.valueOf(rut.getStok()));
        //holder.itemView.setOnClickListener(view -> listener.onSelect(rut));
        if (item.getFoto().size()>0) {

            Glide.with(context)
                    .load(App.getApplication().getResources().getString(R.string.img_end_point) + item.getFoto().get(0).getNamaFile())
//                    .load(App.getApplication().RestService.)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                    .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                    .into(holder.mIconImage);
            Glide.with(context)
                    .load(App.getApplication().getResources().getString(R.string.img_end_point) + item.getFoto().get(0).getNamaFile())
                    .thumbnail(0.1f)
                    .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                    .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                    .into(holder.mIconImageCopy);
        } else Glide.with(context)
                .load(R.drawable.shopping_bag)
                .thumbnail(0.1f)
                .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                .into(holder.mIconImage);
        holder.mCart.setOnClickListener(view -> cartListiner.onItemSelect(item, holder.mIconImageCopy));
    }

//    public void setFilter(List<Item> newList){
//        ruts = new ArrayList<>();
//        ruts.addAll(newList);
//        notifyDataSetChanged();
//    }


    public void setFilter(ArrayList<Items> filterList) {
        items = new ArrayList<>();
        items.addAll(filterList);
        notifyDataSetChanged();
    }
//
    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        else
            return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNama, mHarga, mStok, mToko, mSubsidi;
        LinearLayout mCart;
        ImageView mIconImage, mIconImageCopy;

        ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mSubsidi = view.findViewById(R.id.mSubsidi);
            mCart = view.findViewById(R.id.mCart);
            mStok = view.findViewById(R.id.mStok);
            mIconImage = view.findViewById(R.id.icon_image);
            mIconImageCopy = view.findViewById(R.id.icon_image_copy);
            mHarga = view.findViewById(R.id.mHarga);
            mToko = view.findViewById(R.id.mToko);
//            mTime = view.findViewById(R.id.mTime);
        }
    }

}
