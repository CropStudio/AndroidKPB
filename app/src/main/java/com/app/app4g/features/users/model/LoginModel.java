package com.app.app4g.features.users.model;

public class LoginModel implements IUserLogin {
    String nik, password;

    public LoginModel(String nik, String password){
        this.nik = nik;
        this.password = password;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getNik() {
        return nik;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
