package com.example.app4g.features.petani.jatah;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.app4g.R;
import com.example.app4g.Utils.GsonHelper;
import com.example.app4g.adapter.AdapterPupuk;
import com.example.app4g.data.DataPupuk;
import com.example.app4g.features.petani.MenuUtama;
import com.example.app4g.features.petani.detailpupuk.DetailPupukActivity;
import com.example.app4g.features.users.login.model.LoginResponse;
import com.example.app4g.server.App;
import com.example.app4g.server.Config_URL;
import com.example.app4g.session.Prefs;
import com.example.app4g.ui.SweetDialogs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;


public class ListDataPupuk extends AppCompatActivity {
    @BindView(R.id.main_list_pupuk)
    ListView list;



    ProgressDialog pDialog;

//    ArrayList<DataAnak> newsList = new ArrayList<DataAnak>();
//    AdapterAnak adapter;
    String strIdPupuk, strNamaPupuk, strJenisPupuk, strJmlhPupuk, strKomoditas;
    ArrayList<DataPupuk> newsList = new ArrayList<DataPupuk>();
    AdapterPupuk adapter;

    @BindView(R.id.textNodata)
    TextView noData;
    String nik , token ;
    ImageButton backTomenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_pupuk);
        ButterKnife.bind(this);
        //getSupportActionBar().hide(); //untuk menghilangkan action bar yg di atas
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        newsList.clear();
        adapter = new AdapterPupuk(this, newsList);
        list.setAdapter(adapter);
        list.setEmptyView(noData);

        getNamaPupuk();

        backTomenu = findViewById(R.id.backTo);
        backTomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ListDataPupuk.this, MenuUtama.class);
                startActivity(a);
                finish();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent a = new Intent(ListDataPupuk.this, DetailPupukActivity.class);
                a.putExtra("urea1", newsList.get(position).ureaMt1);
                a.putExtra("urea2", newsList.get(position).ureaMt2);
                a.putExtra("urea3", newsList.get(position).ureaMt3);
                a.putExtra("sp361", newsList.get(position).sp36Mt1);
                a.putExtra("sp362", newsList.get(position).sp36Mt2);
                a.putExtra("sp363", newsList.get(position).sp36Mt3);
                a.putExtra("za1", newsList.get(position).zaMt1);
                a.putExtra("za2", newsList.get(position).zaMt2);
                a.putExtra("za3", newsList.get(position).zaMt3);
                a.putExtra("npk1", newsList.get(position).npkMt1);
                a.putExtra("npk2", newsList.get(position).npkMt2);
                a.putExtra("npk3", newsList.get(position).npkMt3);
                a.putExtra("organik1", newsList.get(position).organikMt1);
                a.putExtra("organik2", newsList.get(position).organikMt2);
                a.putExtra("organik3", newsList.get(position).organikMt3);
                a.putExtra("komoditas1", newsList.get(position).komoditasMt1);
                a.putExtra("komoditas2", newsList.get(position).komoditasMt2);
                a.putExtra("komoditas3", newsList.get(position).komoditasMt3);
                startActivity(a);
                finish();
            }
        });

    }


    private void getNamaPupuk() {

        pDialog.setMessage("Loading.....");
        showDialog();

        String tag_json_obj = "json_obj_req";
        StringRequest strReq = new StringRequest(Request.Method.GET,
                Config_URL.dataPupuk+nik,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("CommonRespon: ", response);
                        hideDialog();
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            String rc = jObj.getString("rc");
                            String rm = jObj.getString("message");

                            if(status){
                                String getObject = jObj.getString("result");
                                JSONArray jsonArray = new JSONArray(getObject);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    DataPupuk pupuk = new DataPupuk();
                                    pupuk.setTahunRdkk(jsonObject.getString("year"));
                                    pupuk.setNikPetani(jsonObject.getString("farmer_nik"));
                                    pupuk.setNamaPetani(jsonObject.getString("farmer_name"));
                                    pupuk.setSubsektor(jsonObject.getString("subsector"));
                                    pupuk.setUreaMt1(jsonObject.getString("mt1_urea"));
                                    pupuk.setUreaMt2(jsonObject.getString("mt2_urea"));
                                    pupuk.setUreaMt3(jsonObject.getString("mt3_urea"));
                                    pupuk.setSp36Mt1(jsonObject.getString("mt1_sp36"));
                                    pupuk.setSp36Mt2(jsonObject.getString("mt2_sp36"));
                                    pupuk.setSp36Mt3(jsonObject.getString("mt3_sp36"));
                                    pupuk.setZaMt1(jsonObject.getString("mt1_za"));
                                    pupuk.setZaMt2(jsonObject.getString("mt2_za"));
                                    pupuk.setZaMt3(jsonObject.getString("mt3_za"));
                                    pupuk.setNpkMt1(jsonObject.getString("mt1_npk"));
                                    pupuk.setNpkMt2(jsonObject.getString("mt2_npk"));
                                    pupuk.setNpkMt3(jsonObject.getString("mt3_npk"));
                                    pupuk.setOrganikMt1(jsonObject.getString("mt1_organic"));
                                    pupuk.setOrganikMt2(jsonObject.getString("mt2_organic"));
                                    pupuk.setOrganikMt3(jsonObject.getString("mt3_organic"));
                                    pupuk.setKomoditasMt1(jsonObject.getString("mt1_commodity"));
                                    pupuk.setKomoditasMt2(jsonObject.getString("mt2_commodity"));
                                    pupuk.setKomoditasMt3(jsonObject.getString("mt3_commodity"));
                                    newsList.add(pupuk);
                                }
                            }else {
                                if(rc.equals(Prefs.DEFAULT_INVALID_TOKEN))
                                    SweetDialogs.commonInvalidToken(ListDataPupuk.this, "Gagal Memuat Permintaan",
                                            rm);
                                else
                                    SweetDialogs.commonError(ListDataPupuk.this,"Gagal Memuat Permintaan",rm,string -> {
                                        gotoDashboard();
                                    });
                            }

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){

                Log.e(String.valueOf(getApplication()), "Error : " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "No CommonRespon From Server", Toast.LENGTH_SHORT).show();
                noData.setText("No CommonRespon From Server :(");
                hideDialog();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-access-token", token);
                headers.put("username", nik);
                return headers;
            }
        };

        App.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() { //ini untuk tombol fisik kembali
        Intent a = new Intent(ListDataPupuk.this, MenuUtama.class);
        startActivity(a);
        finish();
    }

    void gotoDashboard(){
        Intent a = new Intent(ListDataPupuk.this, MenuUtama.class);
        startActivity(a);
        finish();
    }
//
//    @OnClick(R.id.btnTambahDataAnak)
//    void btnTambahDataAnak() {
//        Intent a = new Intent(com.example.app4g.features.petani.anak.ListDataAnak.this, InputDataAnak.class);
//        startActivity(a);
//        finish();
//    }



    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
