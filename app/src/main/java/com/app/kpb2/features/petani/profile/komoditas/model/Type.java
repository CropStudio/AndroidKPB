package com.app.kpb2.features.petani.profile.komoditas.model;

import com.google.gson.annotations.SerializedName;

public class Type {
    @SerializedName("_id")
    public String _id ;

    @SerializedName("value")
    public String value ;

    @SerializedName("label")
    public String label ;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
