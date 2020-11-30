package com.app.kpb2.features.gubernur.Model;

import com.google.gson.annotations.SerializedName;

public class Kecamatan_Item {


    @SerializedName("id_kab")
    private String id_kab;

    @SerializedName("id_kec")
    private String id_kec;

    @SerializedName("nama")
    private String nama;



    public void setId_kab(String id_kab){

        this.id_kab = id_kab;
    }

    public String getId_kab(){
        return id_kab;
    }
    public void setId_kec(String id_kec){

        this.id_kec = id_kec;
    }

    public String getId_kec(){
        return id_kec;
    }

    public void setNama(String nama){

        this.nama = nama;
    }

    public String getNama(){

        return nama;
    }
    @Override
    public String toString(){
        return
                "Kecamatan_Item{" +
                        "id_kab= '" + id_kab+ '\'' +
                        ",id_kec= '" + id_kec+ '\'' +
                        ", nama = '" + nama+ '\'' +
                        "}";
    }
}
