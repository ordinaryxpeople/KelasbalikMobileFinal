package com.kelasbalik.kelasbalikmobile.Model.File;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class File {

    @SerializedName("data")
    private List<FileData> fileData;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<FileData> getFileData() {
        return fileData;
    }

    public void setEbookData(List<FileData> fileData) {
        this.fileData = fileData;
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
