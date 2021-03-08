package com.app.kpb2.features.petani.noRekening.model;

import com.google.gson.annotations.SerializedName;

public class Balance {

    @SerializedName("account_name")
    private String account_name ;

    @SerializedName("account_number")
    private String account_number ;

    @SerializedName("available_balance")
    private String available_balance ;

    @SerializedName("currency_code")
    private String currency_code ;

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAvailable_balance() {
        return available_balance;
    }

    public void setAvailable_balance(String available_balance) {
        this.available_balance = available_balance;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
}
