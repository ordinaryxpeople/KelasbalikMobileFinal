package com.kelasbalik.kelasbalikmobile.Model.File;

import com.google.gson.annotations.SerializedName;

public class FileData {

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPengunggah() {
        return pengunggah;
    }

    public void setPengunggah(String pengunggah) {
        this.pengunggah = pengunggah;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    public String getUrlfile() {
        return urlfile;
    }

    public void setUrlfile(String urlfile) {
        this.urlfile = urlfile;
    }

    public String getUrlgambar() {
        return urlgambar;
    }

    public void setUrlgambar(String urlgambar) {
        this.urlgambar = urlgambar;
    }

    @SerializedName("judul")
    private String judul;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("nama")
    private String pengunggah;

    @SerializedName("mapel")
    private String halaman;

    @SerializedName("nama_file")
    private String urlfile;

    @SerializedName("gambar_file")
    private String urlgambar;

    @SerializedName("tipe")
    private String tipefile;

    public String getTipefile() {
        return tipefile;
    }

    public void setTipefile(String tipefile) {
        this.tipefile = tipefile;
    }

    public FileData(String judul, String deskripsi, String pengunggah, String halaman, String urlfile, String urlgambar, String tipefile) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.pengunggah = pengunggah;
        this.halaman = halaman;
        this.urlfile = urlfile;
        this.urlgambar = urlgambar;
        this.tipefile = tipefile;
    }
}
