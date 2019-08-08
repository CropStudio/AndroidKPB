package com.example.app4g.server;

//This class is for storing all URLs as a model of URLs

public class Config_URL
{
    public static String base_URL           = "http://api.kartupetaniberjaya.com/api";
    //public static String base_URL           = "http://192.168.43.156:8400/api";

    public static String fotoProfilUrl      = "http://api.kartupetaniberjaya.com/potopropil/";
    //public static String fotoProfilUrl      = "http://192.168.43.156:8400/potopropil/";
    //users
    public static String login        = base_URL + "/loginpetani";
    public static String registrasi   = base_URL + "/registrasipetani";
    public static String uploadFoto   = base_URL + "/potopropil/";
    public static String crudUser     = base_URL + "/user";

    //petani
    public static String cekPetani    = base_URL + "/petani/";

    //data anak
    public static String dataAnak     = base_URL + "/cekanak/";
    public static String anak         = base_URL + "/anak";

    //data pupuk
    public static String dataPupuk     = base_URL + "/jatah/";

    //poktan
    public static String dataPoktan        = base_URL + "/poktan";
}