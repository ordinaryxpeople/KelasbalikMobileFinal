package com.kelasbalik.kelasbalikmobile.Model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("id_user")
    private String iduser;

    @SerializedName("kode")
    private String kode;

    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    @SerializedName("notelp")
    private String notelp;

    @SerializedName("tlahir")
    private String tlahir;

    @SerializedName("tgllahir")
    private String tgllahir;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("ortu")
    private String ortu;

    @SerializedName("motto")
    private String motto;

    @SerializedName("foto")
    private String foto;

    @SerializedName("id_sekolah")
    private String idsekolah;

    @SerializedName("id_kelas")
    private String idkelas;

    @SerializedName("kelas")
    private String kelas;

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getTlahir() {
        return tlahir;
    }

    public void setTlahir(String tlahir) {
        this.tlahir = tlahir;
    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getOrtu() {
        return ortu;
    }

    public void setOrtu(String ortu) {
        this.ortu = ortu;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdsekolah() {
        return idsekolah;
    }

    public void setIdsekolah(String idsekolah) {
        this.idsekolah = idsekolah;
    }

    public String getIdkelas() {
        return idkelas;
    }

    public void setIdkelas(String idkelas) {
        this.idkelas = idkelas;
    }
}
