package com.app.kpb2.features.gubernur.Model;

import com.google.gson.annotations.SerializedName;

public class Kabupaten_Item {


    @SerializedName("id_kab")
    private String id_kab;

    @SerializedName("id_prov")
    private String id_prov;

    @SerializedName("nama")
    private String nama;

    @SerializedName("id_jenis")
    private String id_jenis;


    public void setId_kab(String id_kab){

        this.id_kab = id_kab;
    }

    public String getId_kab(){
        return id_kab;
    }
    public void setId_prov(String id_prov){

        this.id_prov = id_prov;
    }

    public String getId_prov(){
        return id_prov;
    }

    public void setNama(String nama){

        this.nama = nama;
    }

    public String getNama(){

        return nama;
    }
    public void setId_jenis(String id_jenis){

        this.id_jenis = id_jenis;
    }

    public String getId_jenis(){
        return id_jenis;
    }
    @Override
    public String toString(){
        return
                "Kabupaten_Item{" +
                        "id_kab= '" + id_kab+ '\'' +
                        ",id_prov = '" + id_prov+ '\'' +
                        ", nama = '" + nama+ '\'' +
                        ",id_jenis= '" + id_jenis+ '\'' +
                        "}";
    }
}
