package com.app.kpb2.features.cart.model;

import com.app.kpb2.features.petani.registrasi.model.Area;
import com.google.gson.annotations.SerializedName;

public class Kios {

    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;
    @SerializedName("pihc_code")
    private String pihc_code;
    @SerializedName("sub_district_code")
    private String sub_district_code;
    @SerializedName("id")
    private String id;
    @SerializedName("area")
    private Area area;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPihc_code() {
        return pihc_code;
    }

    public void setPihc_code(String pihc_code) {
        this.pihc_code = pihc_code;
    }

    public String getSub_district_code() {
        return sub_district_code;
    }

    public void setSub_district_code(String sub_district_code) {
        this.sub_district_code = sub_district_code;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
