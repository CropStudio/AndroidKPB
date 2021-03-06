package com.app.kpb2.features.rutDetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.features.e_commerce.EcommerceActivity;
import com.app.kpb2.features.e_commerce.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RutDetailActivity extends AppCompatActivity implements View.OnClickListener {
    List<Item> item ;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_layout)
    RelativeLayout mLoadingIndicator;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut_detail);
        ButterKnife.bind(this);
        item = (List<Item>) getIntent().getExtras().getSerializable("item");

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        initViews();
        RutDetailAdapter adapter = new RutDetailAdapter(item, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mSubmit.setOnClickListener(this);
    }

    public void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
    }

    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    public void goToDashboard(){
        Intent i = new Intent(RutDetailActivity.this, EcommerceActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToDashboard();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mSubmit:
                Toast.makeText(this, "Maaf sementara menu ini belum dapat digunakan!", Toast.LENGTH_SHORT).show();
            break;
        }
    }
}
