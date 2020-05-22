package com.app.app4g.features.petani;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.app4g.R;
import com.app.app4g.adapter.AdapterPoktan;
import com.app.app4g.data.DataPoktan;
import com.app.app4g.server.App;
import com.app.app4g.server.Config_URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPoktan extends AppCompatActivity {

    String poktan, alamatPoktan;
    @BindView(R.id.namaPoktan)
    EditText edPoktan;
    @BindView(R.id.alamat)
    EditText edAlamat;

    Dialog myDialog;

    private static ProgressDialog pDialog;

    AdapterPoktan adapter;
    ListView list;

    ArrayList<DataPoktan> newsList = new ArrayList<DataPoktan>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_poktan);
        //getSupportActionBar().hide();
        ButterKnife.bind(this);

        Intent i     = getIntent();
        poktan       = i.getStringExtra("namapoktan");
        alamatPoktan = i.getStringExtra("alamat");
        edPoktan.setText(poktan);
        edAlamat.setText(alamatPoktan);

        myDialog = new Dialog(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

    }

    @OnClick(R.id.linearPilihPoktan)
    void linearPoktan(){
        listData();
    }


    public void listData(){
        myDialog.setContentView(R.layout.layout_poktan);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pDialog = new ProgressDialog(myDialog.getContext());
        pDialog.setCancelable(false);
        list = myDialog.findViewById(R.id.array_list);
        EditText edtsearch = myDialog.findViewById(R.id.cariNamaAnak);
        newsList.clear();

        adapter = new AdapterPoktan(EditPoktan.this, newsList);
        list.setAdapter(adapter);
        dataPoktan();

        edtsearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<DataPoktan> filteredList = new ArrayList<DataPoktan>();

                for (int i = 0; i < newsList.size(); i++) {

                    final String text = newsList.get(i).getNamaPoktan().toLowerCase();
                    if (text.contains(query)) {

//                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                // TODO Auto-generated method stub
//                                storeRegIdinSharedPref(getApplicationContext(),filteredList.get(position).getNis(),
//                                        filteredList.get(position).getNamaanak(),
//                                        edUserName.getText().toString(), edNamLengkap.getText().toString(), "",
//                                        edPhone.getText().toString());
//                                Intent a = new Intent(RegistrasiActivity.this, RegistrasiActivity.class);
//                                startActivity(a);
//                            }
//                        });
                        filteredList.add(newsList.get(i));
                    }
                }

                adapter = new AdapterPoktan(EditPoktan.this, filteredList);
                list.setAdapter(adapter);
            }
        });

        myDialog.show();
    }

    public void dataPoktan(){

        pDialog.setMessage("Loading.....");
        showDialog();
        String tag_json_obj = "json_obj_req";
        StringRequest strReq = new StringRequest(Request.Method.GET, Config_URL.dataPoktan, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("CommonRespon: ", response);
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean status = jObj.getBoolean("status");

                    if(status == true){

                        String getObject = jObj.getString("message");
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            final JSONObject jsonObject = jsonArray.getJSONObject(i);

                            final DataPoktan news = new DataPoktan();

                            news.setNamaPoktan(jsonObject.getString("nama"));
                            news.setKabupaten(jsonObject.getString("kabupaten"));
                            news.setKecamatan(jsonObject.getString("kecamatan"));
                            news.setDesa(jsonObject.getString("desa"));


//                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    // TODO Auto-generated method stub
//                                    storeRegIdinSharedPref3(getApplicationContext(),newsList.get(position).getNis(),
//                                            newsList.get(position).getNamaanak(),
//                                            edUserName.getText().toString(), edNamLengkap.getText().toString(), "",
//                                            edPhone.getText().toString());
//                                    Intent a = new Intent(RegistrasiActivity.this, RegistrasiActivity.class);
//                                    startActivity(a);
//                                }
//                            });

                            newsList.add(news);
                        }
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
                hideDialog();
            }
        }){
        };

        App.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(EditPoktan.this, EditProfile.class);
        a.putExtra("datanya", "hehe");
        startActivity(a);
        finish();
    }
}
