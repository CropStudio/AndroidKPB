package com.example.app4g.features.gubernur.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Kecamatan {

    @SerializedName("message")
    private List<Kecamatan_Item> message;

    @SerializedName("status")
    private boolean status;

    public void setMessage(List<Kecamatan_Item> message) {
        this.message = message;

    }

    public List<Kecamatan_Item> getMessage()
    {
        return message;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){

        return status;
    }

    @Override
    public String toString(){
        return
                "Response_Kecamatan{" +
                        "message = '" + message+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

}
