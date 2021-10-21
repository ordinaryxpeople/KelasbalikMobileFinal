package com.kelasbalik.kelasbalikmobile.Rest;

import com.kelasbalik.kelasbalikmobile.Model.File.File;
import com.kelasbalik.kelasbalikmobile.Model.Login.Login;
import com.kelasbalik.kelasbalikmobile.Model.OpenSignal.OpenSignal;
import com.kelasbalik.kelasbalikmobile.Model.Tugas.Tugas;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    //Login
    @FormUrlEncoded
    @POST("Login/apiLoginUser")
    Call<Login> loginResponse(
            @Field("kode") String kode,
            @Field("password") String password
    );

    //Tugas
    @FormUrlEncoded
    @POST("Tugas/apireadtugas")
    Call<Tugas> tugasResponse(
            @Field("idkelas") String idkelas,
            @Field("idsekolah") String idsekolah

    );

    //File
    @FormUrlEncoded
    @POST("File/apireadfile")
    Call<File> getFile(
            @Field("idkelas") String idkelas,
            @Field("idsekolah") String idsekolah,
            @Field("tipe") String tipe
    );

    @FormUrlEncoded
    @POST("File/apireadfile")
    Call<File> searchFile(
            @Field("judul") String judul,
            @Field("idkelas") String idkelas,
            @Field("idsekolah") String idsekolah,
            @Field("tipe") String tipe
    );

    @FormUrlEncoded
    @POST("Onesignal/apisendOSID")
    Call<OpenSignal> postOSID(
            @Field("iddevice") String iddevice,
            @Field("iduser") String iduser
    );
}
