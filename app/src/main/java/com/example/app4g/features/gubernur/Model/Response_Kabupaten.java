package com.example.app4g.features.gubernur.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Kabupaten {

    @SerializedName("message")
    private List<Kabupaten_Item> message;

    @SerializedName("status")
    private boolean status;

    public void setMessage(List<Kabupaten_Item> message) {
        this.message = message;

    }

    public List<Kabupaten_Item> getMessage()
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
                "Response_Kabupaten{" +
                        "message = '" + message+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

}
