package com.example.app4g.features.cart;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.app4g.R;
import com.example.app4g.Utils.Utils;
import com.example.app4g.features.e_commerce.model.Item;
import com.example.app4g.server.AppController;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public List<Item> ruts;
    private CartAdapter.OnItemSelected listener;
    private String matkul;
    Activity context;

    public interface OnItemSelected {
        void onSelect(String rut);

        void onCartSelect(String rut);

        void onCheckbox();
    }

    public CartAdapter(List<Item> data, Activity context, OnItemSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener;
    }

    public void onPilihAll() {

    }


    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        CartAdapter.ViewHolder viewHolder = new CartAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {
        final Item rut = ruts.get(position);
        holder.mHarga.setText(Utils.convertRupiah(String.valueOf(rut.getHarga())));
        holder.mNama.setText(rut.getNamaItem());
        holder.mToko.setText(rut.getDistributor().getNama());
        holder.itemView.setOnClickListener(view -> listener.onSelect("ini itemview"));
        if (!rut.getFoto().equals(""))
            Glide.with(context)
                    .load(AppController.getApplication().getResources().getString(R.string.img_end_point) + rut.getFoto())
                    .apply(new RequestOptions().placeholder(R.mipmap.loading_image))
                    .into(holder.mIconImage);
        else Glide.with(context)
                .load(R.drawable.shopping_bag)
                .apply(new RequestOptions().placeholder(R.mipmap.loading_image))
                .into(holder.mIconImage);
        holder.mNama.setOnClickListener(view -> listener.onCartSelect("ini CartSelect"));
        //holder.mCheckbox.setOnClickListener(view ->listener.onCheckbox(rut.getHarga()));
        //holder.mCheckBox.setTag(rut.getHarga());
        //rut.setQty(Integer.parseInt(holder.mQty.getNumber()));
        holder.mCheckBox.setOnCheckedChangeListener(null);
//        holder.mCheckBox.setSelected(rut.isSelected());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rut.setSelected(true);
                } else {
                    rut.setSelected(false);
                }
                rut.setQty(Integer.parseInt(holder.mQty.getNumber()));
                listener.onCheckbox();
            }
        });

        holder.mQty.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                rut.setQty(newValue);
                listener.onCheckbox();

            }
        });
        holder.mCheckBox.setChecked(rut.isSelected());
    }


    @Override
    public int getItemCount() {
        return ruts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox mCheckBox;
        TextView mNama, mHarga, mStok, mToko;
        ElegantNumberButton mQty;
        LinearLayout mCart;
        ImageView mIconImage;

        ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mCheckBox = view.findViewById(R.id.mCheckbox);
            mQty = view.findViewById(R.id.mQty);
//            mCart = view.findViewById(R.id.mCart);
            //mStok = view.findViewById(R.id.mStok);
            mIconImage = view.findViewById(R.id.icon_image);
            mHarga = view.findViewById(R.id.mHarga);
            mToko = view.findViewById(R.id.mToko);
//            mTime = view.findViewById(R.id.mTime);
        }
    }


}
