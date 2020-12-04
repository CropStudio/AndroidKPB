package com.app.kpb2.features.petani.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;


import com.app.kpb2.R;

import java.util.List;

public class AdapterSliderBanner extends PagerAdapter {

    private List<ModelSliderBanner> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterSliderBanner(List<ModelSliderBanner> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_card_banner, container, false);

        CardView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.cardInfoBeasiswa);
        imageView.setBackgroundResource(models.get(position).getImage());
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
