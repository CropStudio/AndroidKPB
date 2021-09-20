package com.app.kpb2.features.cart;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.Utils.LinkedHashMapAdapter;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.cart.model.BankTfResponse;
import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.features.cart.model.Checkout;
import com.app.kpb2.features.cart.model.Kios;
import com.app.kpb2.features.cart.model.Penyuluh;
import com.app.kpb2.features.cart.model.VaBNI_Response;
import com.app.kpb2.features.pasar_bebas.PasarBebasActivity;
import com.app.kpb2.features.petani.noRekening.Rekening;
import com.app.kpb2.features.petani.profile.model.profile;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.app.kpb2.ui.TopSnakbar;
//import com.google.android.gms.location.places.AutocompleteFilter;
//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.ui.PlaceAutocomplete;
//import com.google.android.gms.location.places.AutocompleteFilter;
//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;

import static java.lang.Integer.parseInt;

public class CartActivity extends AppCompatActivity implements ICartView, CartAdapter.OnItemSelected, CompoundButton.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static long randomDigit;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_layout)
    RelativeLayout mLoadingIndicator;

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.mSubtotal)
    TextView mSubtotal;
    @BindView(R.id.mKios)
    TextView mKios;
    @BindView(R.id.txtSubtotal)
    TextView txtSubtotal;
    @BindView(R.id.mSpinnerKios)
    Spinner mSpinnerKios;
    @BindView(R.id.layoutBottom)
    RelativeLayout layoutBottom;
    @BindView(R.id.LayoutSubtotal)
    LinearLayout LayoutSubtotal;
    @BindView(R.id.mCheckout)
    Button mCheckout;
    @BindView(R.id.mUbahKios)
    Button mUbahKios;
    @BindView(R.id.mDataNotFound)
    RelativeLayout mDataNotFound;

    @BindView(R.id.mCheckbox)
    CheckBox mCheckbox;
    CartPresenter presenter;
    public CartAdapter adapter;
    int CartSize = 0;
    String nik, token, idUser, nama;
    public List<Cart> items = null;
    long Subtotal;
    public static int REQUEST_CODE = 1;
    public static final int PICK_UP = 0;
    public static final String apikey = "AIzaSyDk0QgS3V9J4kPXYINKKWwSg9ZWL1lwTjo";
    Dialog dialog;
    ImageButton closePopup;
    private LinkedHashMap<String, String> listkios;
    private LinkedHashMapAdapter<String, String> adapterListKios;
    SweetAlertDialog sweetAlertDialog;
    Number idKios;
    String namaKios, channel, idPenyuluh;
    profile.Area area ;
    Number idPoktan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Keranjang Saya");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Places.initialize(getApplicationContext(), apikey);
        PlacesClient placesClient = Places.createClient(this);
//        placesClient.findAutocompletePredictions()

        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        idUser = (mProfile.getResult().get_id().contains(" "))
                ? mProfile.getResult().get_id() : mProfile.getResult().get_id();
        nama = (mProfile.getResult().getProfile().getNama().contains(" "))
                ? mProfile.getResult().getProfile().getNama() : mProfile.getResult().getProfile().getNama();
        idKios = mProfile.getResult().getProfile().getIdKios();
        idPoktan = mProfile.getResult().getProfile().getId_poktan();
        area = mProfile.getResult().getProfile().getArea();
        if (idKios != null && Integer.parseInt(String.valueOf(idKios)) != 0) {

            namaKios = mProfile.getResult().getProfile().getKios().get(0).getName();

        } else {
            namaKios = "-";

        }
        presenter = new CartPresenter(this);
        this.initViews();

        mCheckout.setOnClickListener(this);


    }


    @Override
    public void initViews() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
        }
        SdkUIFlowBuilder.init()
                .setClientKey(App.getApplication().getString(R.string.clientKey)) // client_key is mandatory
                .setContext(this) // context is mandatory
                .setTransactionFinishedCallback(new TransactionFinishedCallback() {
                    @Override
                    public void onTransactionFinished(TransactionResult result) {
                        // Handle finished transaction here.
                    }
                }) // set transaction finish callback (sdk callback)
                .setMerchantBaseUrl(App.getApplication().getString(R.string.urlMerchant)) //set merchant url (required)
                .enableLog(true) // enable sdk log (optional)
                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255")) // set theme. it will replace theme on snap theme on MAP ( optional)
                .setLanguage("en") //`en` for English and `id` for Bahasa
                .buildSDK();
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        presenter.getCart(nik, token, idUser);
        mCheckout.setEnabled(false);
//        mSearchAlamat.setOnClickListener(view->showPlaceAutoComplete(REQUEST_CODE));
        mCheckout.setBackgroundColor(getResources().getColor(R.color.grey));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
        mSpinnerKios.setOnTouchListener(spinnerOnTouch);

        mKios.setText(namaKios);
        mUbahKios.setOnClickListener(view -> this.gotoUpdateKios());

    }

    private View.OnTouchListener spinnerOnTouch = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                //Your code
//                presenter.getListKios(nik);
            }
            return false;
        }
    };

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onDataReady(List<Cart> carts) {
        //CartSize = carts.getItem().size();
        items = carts;
        if (!carts.isEmpty()) {
            adapter = new CartAdapter(items, this, this);
            mRecyclerView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
            mCheckbox.setOnCheckedChangeListener(this);
            mCheckbox.setOnClickListener(this);
        } else {
            mDataNotFound.setVisibility(View.VISIBLE);
        }

//        presenter.getListKios(nik);
    }

    @Override
    public void onDataListKiosReady(List<Kios> kios) {
        if (kios.size() > 0) {
            listkios = new LinkedHashMap<String, String>();
            ArrayList<String> list = new ArrayList<>();
            for (Kios value : kios) {
                listkios.put(value.getId(), value.getName());
            }
            adapterListKios = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listkios);
            adapterListKios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinnerKios.setAdapter(adapterListKios);
            mSpinnerKios.setOnItemSelectedListener(this);
        } else {
            SweetDialogs.commonWarning(this, "Peringatan", "anda belum terdaftar di kios manapun ,  ", false);
        }
    }

    public static int showRandomInteger(int aStart, int aEnd, Random aRandom) {
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        int randomNumber = (int) (fraction + aStart);
//        randomDigit = randomNumber ;
        return randomNumber;

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCheckout() {
        List<Checkout> checkout = new ArrayList<>();
        checkout.clear();
        int START = 001;
        int END = 999;
        Random random = new Random();
        randomDigit = showRandomInteger(START, END, random);


        List<Cart> items = adapter.carts.stream()
                .filter(status -> status.isSelected() == true)
                .collect(Collectors.toList());
        for (Cart item : items) {
            Checkout checkouts = new Checkout();
            if (channel.startsWith("Transfer")) {
                checkouts.setIdUnique(String.valueOf(randomDigit));
            }
            checkouts.setNama(nama);
            checkouts.setIdPoktan(String.valueOf(idPoktan));
            checkouts.setFeeBita(item.getItem().getFeeBita());
            checkouts.setFeeKios(item.getItem().getFeeKios());
            checkouts.setFeePenyuluh(item.getItem().getFeePenyuluh());
            checkouts.setHargaDistributor(item.getItem().getHargaDistributor());
            checkouts.setTotalFeeBita(item.getItem().getFeeBita() * item.getJumlahBeli());
            checkouts.setTotalFeeKios(item.getItem().getFeeKios() * item.getJumlahBeli());
            checkouts.setTotalFeePenyuluh(item.getItem().getFeePenyuluh() * item.getJumlahBeli());
            checkouts.setTotalHargaDistributor(item.getItem().getHargaDistributor() * item.getJumlahBeli());
            checkouts.setIdUser(item.getIdUser());
            checkouts.setIdPenyuluh(idPenyuluh);
            checkouts.setJenisPembayaran("Non-Tunai");
            checkouts.setIdPenjual(item.getIdPenjual());
            checkouts.setIdBarang(item.getIdBarang());
            checkouts.setNamaBarang(item.getItem().getNamaBarang());
            checkouts.setSatuan(item.getItem().getSatuan());
            checkouts.setJenis(item.getItem().getJenis());
            checkouts.setHargaBarang(item.getItem().getHargaBarang());
            checkouts.setJumlahBeli(item.getJumlahBeli());
            checkouts.setSubTotal(item.getItem().getHargaBarang() * item.getJumlahBeli());
            checkouts.setStok(item.getItem().getStok());
            checkouts.setIdPemesan(item.getIdUser());
            checkouts.setArea(area);
            checkouts.setChannel(channel);
            checkouts.setIdDistributor(item.getItem().getIdDistributor());
            checkouts.setIdKios(idKios);
            checkouts.setGrandTotalWithIdUnique(Subtotal - randomDigit);
            checkouts.setUrl(App.getApplication().getString(R.string.urlMerchant));
            checkout.add(checkouts);

        }
        Gson gson = new Gson();
        String Data = gson.toJson(checkout);
        Log.d("datacheckout", Data);

        presenter.onCheckout(checkout, nik, token);

    }

    @Override
    public void onCheckoutSuccess(CommonRespon response) {
        Log.v("Message", response.getmRm());
        showMidtrans(response.getMidtrans().getRedirect_url());
//        SweetDialogs.commonSuccessWithIntent(this, "Berhasil Melakukan Checkout", string -> {
//            this.goToCart();
//        });
    }

    @Override
    public void onCheckoutVaBNISuccess(VaBNI_Response response) {
        showTutorVaBNI(response.getResult());
    }

    @Override
    public void onRequestFailed(int rc, String rm) {
        Log.d("rmny", rm);
        if (rc == 401)
            SweetDialogs.commonInvalidToken(this, "Gagal Memuat Permintaan",
                    rm);
        else

            SweetDialogs.commonError(this, "Gagal Memuat Permintaan", rm, string -> {
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
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
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
        Intent i = new Intent(this, PasarBebasActivity.class);
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

        Subtotal = getSubtotal();
        mSubtotal.setText(Utils.convertRupiah(String.valueOf(getSubtotal())));

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private long getSubtotal() {
        long subTotal = adapter.carts.stream().filter(word -> word.isSelected() == true).mapToLong(i -> i.getItem().getHargaBarang() * i.getJumlahBeli()).sum();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
//        boolean checked = ((CheckBox) v).isChecked();
        switch (v.getId()) {
            case R.id.mCheckout:

                if (!namaKios.equals("-")) {
                    String kios =  "<html><b>"+namaKios;
                    SweetDialogs.confirmDialog(this, "Apakah Anda Yakin ? ", "anda memilih kios " + Html.fromHtml(kios) , "Berhasil Melakukan Checkout!", string -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            this.onCheckout();
                            SweetDialogs.confirmDialog(this, "Anda Yakin ?", "Apabila proses pembayaran dibatalkan maka barang akan hilang dari keranjang anda", "Berhasil Melakukan Checkout!", vol -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            this.onCheckout();
                                    this.presenter.getIdPenyuluh(nik, token, idPoktan);

                                }
                            });


                        }
                    });


                } else
                    SweetDialogs.commonWarningWithIntent(this, "Gagal Melakukan Transaksi", "anda belum terdaftar di kios manapun, silahkan memilih kios terlebih dahulu", view -> this.gotoUpdateKios());
                break;
        }
    }

    public void gotoUpdateKios() {
        Intent i = new Intent(this, Rekening.class);
        i.putExtra("className", this.getClass().getSimpleName());
        startActivity(i);
        finish();

    }

    @Override
    public void showNotaBanktf(BankTfResponse result) {
        dialog = new Dialog(this, R.style.AppDialogTheme);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogTheme;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_nota_banktf);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.40);
        dialog.getWindow().setLayout(width, height);

        TextView norek = dialog.findViewById(R.id.mNorek);
        TextView jumlah = dialog.findViewById(R.id.mJumlah);
        TextView order_id = dialog.findViewById(R.id.mOrderId);
        TextView expired = dialog.findViewById(R.id.expired);
        norek.setText("988198825");
        long grandTot = result.getGrandtotal();
        long total = result.getGrandtotal() + randomDigit;
        jumlah.setText(Utils.convertRupiah(String.valueOf(total)));
        order_id.setText(result.getResult().get(0).getOrderId());

        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        closePopup = dialog.findViewById(R.id.closePopKeuntungan);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                goToCart();

            }
        });
    }

    @Override
    public void showTutorVaBNI(VaBNI_Response.VaBNI result) {
        dialog = new Dialog(this, R.style.AppDialogTheme);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogTheme;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_caratransaksi_vabni);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        dialog.getWindow().setLayout(width, height);
        TabLayout tabLayout = dialog.findViewById(R.id.tabs);
        LinearLayout tab1 = dialog.findViewById(R.id.tab1);
        LinearLayout tab2 = dialog.findViewById(R.id.tab2);
        LinearLayout tab3 = dialog.findViewById(R.id.tab3);
        TextView norek = dialog.findViewById(R.id.mNorek);
        TextView jumlah = dialog.findViewById(R.id.mJumlah);
        TextView order_id = dialog.findViewById(R.id.mOrderId);
        TextView expired = dialog.findViewById(R.id.expired);
        norek.setText(result.getVirtual_account());
        jumlah.setText(Utils.convertRupiah(String.valueOf(result.getGrandTotal())));
        order_id.setText(result.getVirtual_account());
        expired.setText("Harap selesaikan pembayaran sebelum " + Utils.convertMongoDate(result.getDatetime_expired()));
        tabLayout.addTab(tabLayout.newTab().setText("ATM BNI"));
        tabLayout.addTab(tabLayout.newTab().setText("INTERNET BANKING"));
        tabLayout.addTab(tabLayout.newTab().setText("MOBILE BANKING"));
        tabLayout.getTabAt(0).select();
        tab1.setVisibility(View.VISIBLE);
        tab2.setVisibility(View.GONE);
        tab3.setVisibility(View.GONE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tab1.setVisibility(View.VISIBLE);
                        tab2.setVisibility(View.GONE);
                        tab3.setVisibility(View.GONE);
                    }
                } else if (tab.getPosition() == 1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tab1.setVisibility(View.GONE);
                        tab2.setVisibility(View.VISIBLE);
                        tab3.setVisibility(View.GONE);
                    }
                } else {
                    tab1.setVisibility(View.GONE);
                    tab2.setVisibility(View.GONE);
                    tab3.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        closePopup = dialog.findViewById(R.id.closePopKeuntungan);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }


    @Override
    public void showPaymentMethod() {
        dialog = new Dialog(this, R.style.AppDialogTheme);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogTheme;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_showpayment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        CardView card_payment_gateway = dialog.findViewById(R.id.card_payment_gateway);
        CardView card_va_bni = dialog.findViewById(R.id.card_va_bni);
        CardView card_bank_transfer = dialog.findViewById(R.id.card_bank_transfer);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        closePopup = dialog.findViewById(R.id.closePopKeuntungan);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetDialogs.confirmDialog(CartActivity.this, "Batalkan proses pembayaran?", "Apabila proses pembayaran dibatalkan maka barang akan hilang dari keranjang anda", "Berhasil Melakukan Checkout!", string -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dialog.dismiss();

                    }
                });

            }
        });
        card_bank_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetDialogs.confirmDialog(CartActivity.this, "Batalkan proses pembayaran?", "Apabila proses pembayaran dibatalkan maka barang akan hilang dari keranjang anda", "Berhasil Melakukan Checkout!", string -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dialog.dismiss();
                        channel = "Transfer";
                        onCheckout();
//                        goToCart();

                    }
                });

            }
        });
        card_payment_gateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetDialogs.confirmDialog(CartActivity.this, "Batalkan proses pembayaran?", "Apabila proses pembayaran dibatalkan maka barang akan hilang dari keranjang anda", "Berhasil Melakukan Checkout!", string -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dialog.dismiss();
                        channel = "Payment Gateway";
                        onCheckout();
//                        goToCart();

                    }
                });

            }
        });
        card_va_bni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetDialogs.confirmDialog(CartActivity.this, "Batalkan proses pembayaran?", "Apabila proses pembayaran dibatalkan maka barang akan hilang dari keranjang anda", "Berhasil Melakukan Checkout!", string -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dialog.dismiss();
                        channel = "VA BNI";
                        onCheckout();
//                        goToCart();

                    }
                });

            }
        });
    }


    @Override
    public void showMidtrans(String url) {
        dialog = new Dialog(this, R.style.AppDialogTheme);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogTheme;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_showmidtrans);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        dialog.getWindow().setLayout(width, height);

        AdvancedWebView mWebView = dialog.findViewById(R.id.webview);
        mWebView.setListener(CartActivity.this, new AdvancedWebView.Listener() {
            @Override
            public void onPageStarted(String url, Bitmap favicon) {

                mWebView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(String url) {
//                pDialog.dismiss();
                mWebView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageError(int errorCode, String description, String failingUrl) {
                Toast.makeText(CartActivity.this, "onPageError(errorCode = " + errorCode + ",  description = " + description + ",  failingUrl = " + failingUrl + ")", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
                //Toast.makeText(WebViews.this, "onDownloadRequested(url = "+url+",  suggestedFilename = "+suggestedFilename+",  mimeType = "+mimeType+",  contentLength = "+contentLength+",  contentDisposition = "+contentDisposition+",  userAgent = "+userAgent+")", Toast.LENGTH_LONG).show();

                if (AdvancedWebView.handleDownload(CartActivity.this, url, suggestedFilename)) {
                    // download successfully handled
                    //AdvancedWebView.handleDownload(this, url, suggestedFilename);
                    Toast.makeText(getApplicationContext(), "Berhasil didownload", Toast.LENGTH_LONG).show();
                } else {
                    // download couldn't be handled because user has disabled download manager app on the device
                }
            }

            @Override
            public void onExternalPageRequest(String url) {
                Toast.makeText(CartActivity.this, "onExternalPageRequest(url = " + url + ")", Toast.LENGTH_SHORT).show();
            }
        });
        mWebView.setGeolocationEnabled(false);
        mWebView.setMixedContentAllowed(true);
        mWebView.setCookiesEnabled(true);
        mWebView.setThirdPartyCookiesEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

//                pDialog.setMessage("Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setCancelable(false);
//                pDialog.show();
                showLoadingIndicator();
                Log.d("URLNYA", url);
                if (url.startsWith(App.getApplication().getString(R.string.urlMerchant))) {
                    SweetDialogs.commonSuccessWithIntent(CartActivity.this, "Silahkan Selesaikan Pembayaran", string -> {
                        dialog.dismiss();

                    });
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                if(pDialog!=null){
//                    pDialog.dismiss();
//                }
                hideLoadingIndicator();
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //Toast.makeText(WebViews.this, title, Toast.LENGTH_SHORT).show();
                // hideDialog();
            }

        });

        mWebView.addHttpHeader("X-Requested-With", "");
        mWebView.loadUrl(url);

        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        closePopup = dialog.findViewById(R.id.closePopKeuntungan);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetDialogs.confirmDialog(CartActivity.this, "Batalkan proses pembayaran?", "Apabila proses pembayaran dibatalkan maka barang akan hilang dari keranjang anda", "Berhasil Melakukan Checkout!", string -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dialog.dismiss();
//                        this.showPaymentMethod();
                        goToCart();

                    }
                });

            }
        });


    }

    @Override
    public void onDataPenyuluh(Penyuluh result) {
        Log.d("Penyuluh", new Gson().toJson(result));
//        this.showPaymentMethod();
        if (result.getPenyuluh().size() > 0) {
            idPenyuluh = result.getPenyuluh().get(0).getIdPenyuluh();
            this.showPaymentMethod();
        } else {
            SweetDialogs.commonWarning(this, "Gagal memuat permintaan", "tidak dapat melakukan transaksi ,silahkan hubungi Bina Tani ", false);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Map.Entry<String, String> itemProv = (Map.Entry<String, String>) mSpinnerKios.getSelectedItem();
//        idKios = parseInt(itemProv.getKey());
//        namaKios = itemProv.getValue();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
