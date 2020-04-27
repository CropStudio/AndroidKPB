package com.app.app4g.features.petani.registrasi.model.FormModel;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("id_kab")
    private String id_kab ;

    @SerializedName("id_prov")
    private String id_prov ;

    @SerializedName("nama")
    private String nama ;

    @SerializedName("id_kec")
    private String id_kec ;

    @SerializedName("id_kel")
    private String id_kel ;

    @SerializedName("name")
    private String name ;

    @SerializedName("subsector_id")
    private String subsector_id ;

    @SerializedName("id")
    private String id ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubsector_id() {
        return subsector_id;
    }

    public void setSubsector_id(String subsector_id) {
        this.subsector_id = subsector_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_kab() {
        return id_kab;
    }

    public void setId_kab(String id_kab) {
        this.id_kab = id_kab;
    }

    public String getId_prov() {
        return id_prov;
    }

    public void setId_prov(String id_prov) {
        this.id_prov = id_prov;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_kec() {
        return id_kec;
    }

    public void setId_kec(String id_kec) {
        this.id_kec = id_kec;
    }

    public String getId_kel() {
        return id_kel;
    }

    public void setId_kel(String id_kel) {
        this.id_kel = id_kel;
    }
}
