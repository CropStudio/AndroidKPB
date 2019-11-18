package com.example.app4g.features.rut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.app4g.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RutActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut);
        ButterKnife.bind(this);
        mToolbar.setTitle("RUT (Rencana Usaha Tani)");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.c_white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
