package com.app.kpb2.features.cart.model;

import com.app.kpb2.features.e_commerce.model.Item;
import com.app.kpb2.features.pasar_bebas.model.Items;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {
    @SerializedName("_id")
    private String _id;
    @SerializedName("idBarang")
    private String idBarang;
    @SerializedName("jumlahBeli")
    private int jumlahBeli;
    @SerializedName("idUser")
    private String idUser;
    @SerializedName("idPenjual")
    private String idPenjual;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("update_at")
    private String update_at;
    @SerializedName("barang")
    private Items item;

    @SerializedName("selected")
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdPenjual() {
        return idPenjual;
    }

    public void setIdPenjual(String idPenjual) {
        this.idPenjual = idPenjual;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }
}
