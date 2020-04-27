package com.app.app4g.features.e_commerce;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.app.app4g.R;
import com.app.app4g.Utils.Utils;
import com.app.app4g.features.e_commerce.model.Item;
import com.app.app4g.features.e_commerce.model.Product;
import com.app.app4g.server.App;

import java.util.ArrayList;
import java.util.List;


public class EcommerceAdapter extends RecyclerView.Adapter<EcommerceAdapter.ViewHolder> {
    private List<Item> ruts;
    private List<Item> filterList;
    private EcommerceAdapter.OnItemSelected listener;
    private EcommerceAdapter.onCartSelected cartListiner;
    private String matkul;
    Activity context;

    public interface OnItemSelected {
        void onSelect(Product product);
    }

    public interface onCartSelected {
        void onCartSelect(Item rut, ImageView image);
    }

    public EcommerceAdapter(List<Item> data, Activity context, onCartSelected cartListiner) {
        this.ruts = data;
        this.context = context;
        this.cartListiner = cartListiner;
    }


    @Override
    public EcommerceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        EcommerceAdapter.ViewHolder viewHolder = new EcommerceAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final EcommerceAdapter.ViewHolder holder, final int position) {
        final Item rut = ruts.get(position);
        //holder.mkdmatkul.setText(laporan.get);
        if (rut.getKategori().equals("Subsidi"))
            holder.mSubsidi.setVisibility(View.VISIBLE);
        else
            holder.mSubsidi.setVisibility(View.GONE);
        holder.mHarga.setText(Utils.convertRupiah(String.valueOf(rut.getHarga())));
        holder.mNama.setText(rut.getNamaItem());
        holder.mToko.setText(rut.getDistributor().getNama());
        holder.mStok.setText(String.valueOf(rut.getStok()));
        //holder.itemView.setOnClickListener(view -> listener.onSelect(rut));
        if (!rut.getFoto().equals("")) {
            Glide.with(context)
                    .load(App.getApplication().getResources().getString(R.string.img_end_point) + rut.getFoto())
                    .thumbnail(0.1f)
                    .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                    .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                    .into(holder.mIconImage);
            Glide.with(context)
                    .load(App.getApplication().getResources().getString(R.string.img_end_point) + rut.getFoto())
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
        holder.mCart.setOnClickListener(view -> cartListiner.onCartSelect(rut, holder.mIconImageCopy));
    }

//    public void setFilter(List<Item> newList){
//        ruts = new ArrayList<>();
//        ruts.addAll(newList);
//        notifyDataSetChanged();
//    }


    public void setFilter(ArrayList<Item> filterList) {
        ruts = new ArrayList<>();
        ruts.addAll(filterList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
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
