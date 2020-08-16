package com.app.app4g.features.rut.editRut2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.features.petani.profile.model.DataMt;
import com.app.app4g.features.rut.model.KebutuhanSaprotan;
import com.app.app4g.features.rut.model.ListBarang;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RutEditActivity extends AppCompatActivity implements View.OnTouchListener, AdapterView.OnItemClickListener {
    @BindView(R.id.mContainer)
    LinearLayout mContainer;
    @BindView(R.id.mBtnEdit)
    Button mBtnEdit;
    List<KebutuhanSaprotan> kebutuhanSaprotans;
    ArrayList<ListBarang> listBarangs;
    JSONObject data = new JSONObject();
    Spinner mSpinner;
    View rowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut_edit);
        ButterKnife.bind(this);
        kebutuhanSaprotans = (List<KebutuhanSaprotan>) getIntent().getExtras().getSerializable("kebutuhanSaprotan");


        for (KebutuhanSaprotan saprotan : kebutuhanSaprotans) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.spinner_component, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 25, 0, 0);
            rowView.setLayoutParams(layoutParams);
            mSpinner = rowView.findViewById(R.id.mSpinner);
            final TextView mNama = rowView.findViewById(R.id.mNama);
            mNama.setText(saprotan.getNama());

            for (ListBarang barang : saprotan.getListBarang()) {
                Log.d("ListBarang ", new Gson().toJson(barang.getNamaBarang()));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, Collections.singletonList(barang.getNamaBarang()));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);


            }

            mContainer.addView(rowView, 0);

        }
        mSpinner.setOnItemClickListener(this);




        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("datanya", data.toString());
                Toast.makeText(RutEditActivity.this, "EDIT", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSpinnerSelect(View v){

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("ontouch","hiyahhhh" );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return false;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "diklikspinnernya", Toast.LENGTH_SHORT).show();
    }
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        try {
//            data.put("name", mSpinner.getSelectedItem());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Log.d("VIEWNYA" , String.valueOf(view.getId()));
//        switch (view.getId()){
//            case R.id.mSpinner:
//                try {
//                    data.put("name" , mSpinner.getSelectedItem());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }


}
