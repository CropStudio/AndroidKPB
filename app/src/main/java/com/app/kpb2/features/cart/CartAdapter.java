package com.app.kpb2.features.cart;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
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
import com.app.kpb2.R;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.e_commerce.model.Item;
import com.app.kpb2.server.App;

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
        if (rut.getTipe().equals("Subsidi")) {
            holder.mSubsidi.setVisibility(View.VISIBLE);
            holder.mStok.setText("Sisa Subsidi : " +rut.getStok() + " " + rut.getSatuan());
        } else {
            holder.mSubsidi.setVisibility(View.GONE);
            holder.mStok.setText("Stok : " +rut.getStok() + " " + rut.getSatuan());
        }
        holder.mHarga.setText(Utils.convertRupiah(String.valueOf(rut.getHarga())));
        holder.mNama.setText(rut.getNamaItem());
        holder.mToko.setText(rut.getKios());

        holder.itemView.setOnClickListener(view -> listener.onSelect("ini itemview"));
        if (!rut.getFoto().equals(""))
            Glide.with(context)
                    .load(App.getApplication().getResources().getString(R.string.img_end_point) + rut.getFoto())
                    .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
                    .into(holder.mIconImage);
        else Glide.with(context)
                .load(R.drawable.shopping_bag)
                .apply(new RequestOptions().placeholder(R.drawable.loading_ios))
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
        TextView mNama, mHarga, mStok, mToko, mSubsidi;
        ElegantNumberButton mQty;
        LinearLayout mCart;
        ImageView mIconImage;

        ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mCheckBox = view.findViewById(R.id.mCheckbox);
            mQty = view.findViewById(R.id.mQty);
            mSubsidi = view.findViewById(R.id.mSubsidi);
//            mCart = view.findViewById(R.id.mCart);
            mStok = view.findViewById(R.id.mStok);
            mIconImage = view.findViewById(R.id.icon_image);
            mHarga = view.findViewById(R.id.mHarga);
            mToko = view.findViewById(R.id.mToko);
//            mTime = view.findViewById(R.id.mTime);
        }
    }


}
