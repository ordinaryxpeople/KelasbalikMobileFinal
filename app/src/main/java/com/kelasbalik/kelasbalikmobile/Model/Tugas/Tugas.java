package com.kelasbalik.kelasbalikmobile.Model.Tugas;

import com.google.gson.annotations.SerializedName;
import com.kelasbalik.kelasbalikmobile.Model.Login.LoginData;

import java.util.List;

public class Tugas {

    @SerializedName("data")
    private List<TugasData> tugasData;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<TugasData> getTugasData() {
        return tugasData;
    }

    public void setTugasData(List<TugasData> tugasData) {
        this.tugasData = tugasData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
