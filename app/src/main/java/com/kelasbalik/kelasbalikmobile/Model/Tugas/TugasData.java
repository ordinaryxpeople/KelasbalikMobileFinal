package com.kelasbalik.kelasbalikmobile.Model.Tugas;

import com.google.gson.annotations.SerializedName;

public class TugasData {

    public String getIdtugas() {
        return idtugas;
    }

    public void setIdtugas(String idtugas) {
        this.idtugas = idtugas;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getPengajar() {
        return pengajar;
    }

    public void setPengajar(String pengajar) {
        this.pengajar = pengajar;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTglmulai() {
        return tglmulai;
    }

    public void setTglmulai(String tglmulai) {
        this.tglmulai = tglmulai;
    }

    public String getTglselesai() {
        return tglselesai;
    }

    public void setTglselesai(String tglselesai) {
        this.tglselesai = tglselesai;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    @SerializedName("id_tugas")
    private String idtugas;

    @SerializedName("judul")
    private String judul;

    @SerializedName("kelas")
    private String kelas;

    @SerializedName("nama")
    private String pengajar;

    @SerializedName("halaman")
    private String halaman;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("tglmulai")
    private String tglmulai;

    @SerializedName("tglselesai")
    private String tglselesai;

    @SerializedName("mapel")
    private String mapel;

    public TugasData(String idtugas, String judul, String kelas, String pengajar, String halaman, String keterangan, String tglmulai, String tglselesai, String mapel) {
        this.idtugas = idtugas;
        this.judul = judul;
        this.kelas = kelas;
        this.pengajar = pengajar;
        this.halaman = halaman;
        this.keterangan = keterangan;
        this.tglmulai = tglmulai;
        this.tglselesai = tglselesai;
        this.mapel = mapel;
    }
}
