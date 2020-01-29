package com.example.app4g.server;

//This class is for storing all URLs as a model of URLs

import com.example.app4g.R;

public class Config_URL
{
    //public static String base_URL           = "http://172.32.1.93:5050";
//    public static String base_URL           = "http://103.230.48.151:5050";
    public static String base_URL           = App.getApplication().getResources().getString(R.string.end_point);
//
    public static String fotoProfilUrl      = "http://api.kartupetaniberjaya.com/potopropil/";
    //public static String fotoProfilUrl      = "http://192.168.43.156:8400/potopropil/";
    //Users
    public static String login        = base_URL + "Users/signin";
    public static String registrasi   = base_URL + "Users/signup";
    public static String uploadFoto   = base_URL + "potopropil/";
    public static String crudUser     = base_URL + "user";

    //petani
    public static String cekPetani    = base_URL + "cekPetani/";

    //data anak
    public static String dataAnak     = base_URL + "cekanak/";
    public static String anak         = base_URL + "anak";

    //data pupuk
    public static String dataPupuk     = base_URL + "e-rdkk/";

    //poktan
    public static String dataPoktan   = base_URL + "poktan";
}