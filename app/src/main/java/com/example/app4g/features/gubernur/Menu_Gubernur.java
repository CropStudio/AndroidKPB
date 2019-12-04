package com.example.app4g.features.gubernur;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.app4g.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu_Gubernur extends AppCompatActivity {

    private BarChart mBarChart;

    String[] kabupaten = {"pilih Kabupaten", "Kota Bandar Lampung", "Kota Metro", "Way kana", "Pringsewu"};
    String[] Kecamatan = {"pilih kecamatan", "Kedaton", "Labuhan Ratu", "Teluk", "Sukabumi"};
    String[] Kelompoktani = {"pilih Kelompok tani", "1", "2", "3", "4"};
    String[] Status = {"pilih Status Pengajuan", "GOLD", "Silver"};

    Spinner Pilih_kabupaten, Pilih_kecamatan, Kelompok_tani, status;

    @BindView(R.id.grafik1) ImageView Grafik;
    @BindView(R.id.profile1) ImageView Profile;
    @BindView(R.id.ivHome1) ImageView home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__gubernur);
        mBarChart = findViewById(R.id.chart);
        ButterKnife.bind(this);

        Pilih_kabupaten = findViewById(R.id.kabupaten);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, kabupaten);
        Pilih_kabupaten.setAdapter(adapter);


        Pilih_kecamatan = findViewById(R.id.kecamatan);
        ArrayAdapter<String> kecamatan = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Kecamatan);
        Pilih_kecamatan.setAdapter(kecamatan);

        Kelompok_tani = findViewById(R.id.kelompoktani);
        ArrayAdapter<String> tani = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Kelompoktani);
        Kelompok_tani.setAdapter(tani);


        status = findViewById(R.id.txt_status);
        ArrayAdapter<String> Statuss = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Status);
        status.setAdapter(Statuss);


        float groupSpace = 0.011f;
        float barSpace = 0.04f;
        float barWidth = 0.48f;
        float tahunAwal = 2016f;

        // Data-data yang akan ditampilkan di Chart
        List<BarEntry> datasatu = new ArrayList<BarEntry>();
        datasatu.add(new BarEntry(2016, 1400000));

        List<BarEntry> datadua = new ArrayList<BarEntry>();
        datadua.add(new BarEntry(2016, 300000));

        List<BarEntry> datatiga = new ArrayList<BarEntry>();
        datatiga.add(new BarEntry(2016, 5700000));

        List<BarEntry> dataempat = new ArrayList<BarEntry>();
        dataempat.add(new BarEntry(2016, 1500000));

        List<BarEntry> datalima = new ArrayList<BarEntry>();
        datalima.add(new BarEntry(2016, 3600000));


        List<BarEntry> dataenam = new ArrayList<BarEntry>();
        dataenam.add(new BarEntry(2016, 200000));


        List<BarEntry> datatujuh = new ArrayList<BarEntry>();
        datatujuh.add(new BarEntry(2016, 1700000));

        List<BarEntry> datadelapan = new ArrayList<BarEntry>();
        datadelapan.add(new BarEntry(2016, 500000));


        // Pengaturan atribut bar, seperti warna dan lain-lain
        BarDataSet dataSet1 = new BarDataSet(datasatu, "Jumlah Poktan");
        dataSet1.setColor(ColorTemplate.getHoloBlue());

        BarDataSet dataSet2 = new BarDataSet(datadua, "Total Penerima");
        dataSet2.setColor(ColorTemplate.COLORFUL_COLORS[1]);

        BarDataSet dataSet3 = new BarDataSet(datatiga, "Luas Tanah(HA)");
        dataSet3.setColor(ColorTemplate.JOYFUL_COLORS[1]);

        BarDataSet dataSet4 = new BarDataSet(dataempat, "Pupuk Urea");
        dataSet4.setColor(Color.GREEN);

        BarDataSet dataSet5 = new BarDataSet(datalima, "Pupuk ZA (Kg)");
        dataSet5.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);

        BarDataSet dataSet6 = new BarDataSet(dataenam, "Pupuk SP36(KG)");
        dataSet6.setColor(ColorTemplate.LIBERTY_COLORS[1]);

        BarDataSet dataSet7 = new BarDataSet(datatujuh, "Pupuk NPK");
        dataSet7.setColor(ColorTemplate.PASTEL_COLORS[0]);

        BarDataSet dataSet8 = new BarDataSet(datadelapan, "Pupuk Organik(Kg)");
        dataSet8.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);


        // Membuat Bar data yang akan di set ke Chart
        BarData barData = new BarData(dataSet1, dataSet2, dataSet3, dataSet4, dataSet5, dataSet6, dataSet7, dataSet8);

        // Pengaturan sumbu X
        XAxis xAxis = mBarChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(false);

        // Agar ketika di zoom tidak menjadi pecahan
        xAxis.setGranularity(1f);

        // Diubah menjadi integer, kemudian dijadikan String
        // Ini berfungsi untuk menghilankan koma, dan tanda ribuah pada tahun
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value);
            }
        });

        //Menghilangkan sumbu Y yang ada di sebelah kanan
        mBarChart.getAxisRight().setEnabled(false);

        // Menghilankan deskripsi pada Chart
        mBarChart.getDescription().setEnabled(false);

        // Set data ke Chart
        // Tambahkan invalidate setiap kali mengubah data chart
        mBarChart.setData(barData);
        mBarChart.getBarData().setBarWidth(barWidth);
        mBarChart.getXAxis().setAxisMinimum(tahunAwal);
        mBarChart.getXAxis().setAxisMaximum(tahunAwal + mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * 1);
        mBarChart.groupBars(tahunAwal, groupSpace, barSpace);
        mBarChart.setDragEnabled(true);
        mBarChart.invalidate();
    }
    @OnClick(R.id.grafik1)
    public void submit_pindahgrafik() {
        Intent inten=new Intent(Menu_Gubernur.this,Menu_Gubernur.class);
        startActivity(inten);
    }

    @OnClick(R.id.ivHome1)
    public  void submit_pindahdashboard(){
        Intent intent=new Intent(Menu_Gubernur.this,Dashboard_Gubernur.class);
        startActivity(intent);
    }

    @OnClick(R.id.profile1)
    public void submit_pindahprofle (){
        Intent intent=new Intent(Menu_Gubernur.this,ProfileFragmentGubernur.class);
        startActivity(intent);

    }
}
