package com.app.kpb2.features.cart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.features.cart.model.Checkout;
import com.app.kpb2.common.CommonResponse;
import com.app.kpb2.features.e_commerce.EcommerceActivity;
import com.app.kpb2.features.e_commerce.model.Item;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements ICartView, CartAdapter.OnItemSelected, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_layout)
    RelativeLayout mLoadingIndicator;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mSubtotal)
    TextView mSubtotal;
    @BindView(R.id.txtSubtotal)
    TextView txtSubtotal;
    @BindView(R.id.layoutBottom)
    RelativeLayout layoutBottom;
    @BindView(R.id.LayoutSubtotal)
    LinearLayout LayoutSubtotal;
    @BindView(R.id.mCheckout)
    Button mCheckout;
    @BindView(R.id.mDataNotFound)
    RelativeLayout mDataNotFound;

    @BindView(R.id.mCheckbox)
    CheckBox mCheckbox;
    CartPresenter presenter;
    public CartAdapter adapter;
    int CartSize = 0;
    String nik,token ;
    public List<Item> items = null;
    long Subtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        mToolbar.setTitle("Keranjang Saya");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.c_white));
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();

        presenter = new CartPresenter(this);
        presenter.getCart(nik,token);
        mCheckout.setOnClickListener(this);
        this.initViews();

    }



    @Override
    public void initViews() {
        mCheckout.setEnabled(false);
        mCheckout.setBackgroundColor(getResources().getColor(R.color.grey));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onDataReady(Cart carts) {
        //CartSize = carts.getItem().size();
        items = carts.getItem();
        if (!carts.getItem().isEmpty()) {
            adapter = new CartAdapter(carts.getItem(), this, this);
            mRecyclerView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
            mCheckbox.setOnCheckedChangeListener(this);
            mCheckbox.setOnClickListener(this);
        } else {
            mDataNotFound.setVisibility(View.VISIBLE);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCheckout() {

        Checkout checkouts = new Checkout();

        List<Item> items = adapter.ruts.stream()
                .filter(status -> status.isSelected == true)
                .collect(Collectors.toList());
        checkouts.setNik(nik);
        checkouts.setGrandtotal(Subtotal);
        checkouts.setItem(items);
        Gson gson = new Gson();
        String Data = gson.toJson(checkouts);

        presenter.onCheckout(Data,nik,token);

    }

    @Override
    public void onCheckoutSuccess(CommonResponse response){
        Log.v("Message" , response.getRm());
        SweetDialogs.commonSuccessWithIntent(this, "Berhasil Melakukan Checkout" , string -> {
            this.goToCart();
        });
    }
    @Override
    public void onRequestFailed(String rc, String rm) {
        if(rc.equals(Prefs.DEFAULT_INVALID_TOKEN))
            SweetDialogs.commonInvalidToken(this, "Gagal Memuat Permintaan",
                    rm);
        else
            SweetDialogs.commonError(this,"Gagal Memuat Permintaan",rm,string -> {
                this.goToCart();
            });

    }


    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToDashboard();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            goToDashboard();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, EcommerceActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void goToCart() {
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onSelect(String rut) {
        //Toast.makeText(this, rut, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCartSelect(String rut) {
        //Toast.makeText(this, rut, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCheckbox() {
        int Size = 0;

//        for (Item x : adapter.ruts) {
//            if (x.isSelected()) {
//                Size++;
//            }
//        }
//        if (CartSize == Size)
//            mCheckbox.setChecked(true);
//        else
//            mCheckbox.setChecked(false)l
        Subtotal = getSubtotal();
        mSubtotal.setText(Utils.convertRupiah(String.valueOf(getSubtotal())));

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private long getSubtotal() {
//        for (Item x : adapter.ruts) {
//            if (x.isSelected()) {
//                hargaPerItem = x.getHarga() * x.getQty();
//                subTotal += hargaPerItem;
//            }
//
//        }
        long subTotal = adapter.ruts.stream().filter(word -> word.isSelected == true).mapToLong(i -> i.getHarga() * i.getQty()).sum();
        if (subTotal != 0) {
            mCheckout.setEnabled(true);
            mCheckout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            mCheckout.setEnabled(false);
            mCheckout.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        return subTotal;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
//        boolean checked = ((CheckBox) v).isChecked();
        switch (v.getId()) {
            case R.id.mCheckout:
                SweetDialogs.confirmDialog(this, "Apakah Anda Yakin !" , "Melakukan Checkout ?" , "Berhasil Melakukan Checkout!", string -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        this.onCheckout();
                    }
                });
                break;
        }
    }
}
