package com.example.app4g.features.petani.registrasi.model;

import com.google.gson.annotations.SerializedName;

public class Area {
    @SerializedName("province")
    private String province ;

    @SerializedName("province_code")
    private String province_code ;

    @SerializedName("city")
    private String city ;

    @SerializedName("city_code")
    private String city_code ;

    @SerializedName("district")
    private String district ;

    @SerializedName("district_code")
    private String district_code ;

    @SerializedName("sub_district")
    private String sub_district ;

    @SerializedName("sub_district_code")
    private String sub_district_code ;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getSub_district() {
        return sub_district;
    }

    public void setSub_district(String sub_district) {
        this.sub_district = sub_district;
    }

    public String getSub_district_code() {
        return sub_district_code;
    }

    public void setSub_district_code(String sub_district_code) {
        this.sub_district_code = sub_district_code;
    }
}
