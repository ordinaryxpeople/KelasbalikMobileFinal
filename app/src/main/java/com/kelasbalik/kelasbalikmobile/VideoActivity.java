package com.kelasbalik.kelasbalikmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kelasbalik.kelasbalikmobile.Adapter.File.FileAdapter;
import com.kelasbalik.kelasbalikmobile.Config.SessionManager;
import com.kelasbalik.kelasbalikmobile.Model.File.File;
import com.kelasbalik.kelasbalikmobile.Model.File.FileData;
import com.kelasbalik.kelasbalikmobile.Rest.ApiClient;
import com.kelasbalik.kelasbalikmobile.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    SessionManager sessionManager;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static VideoActivity videoActivity;
    EditText etSearchVideo;
    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        sessionManager = new SessionManager(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvVideo);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        videoActivity=this;
        imageViewBack = (ImageView) findViewById(R.id.ivBackButtonVideo);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String kelasuser = sessionManager.getUserDetail().get(SessionManager.IDKELASUSER);
        String sekolahuser = sessionManager.getUserDetail().get(SessionManager.IDSEKOLAHUSER);

        refresh(kelasuser,sekolahuser,"2");

        etSearchVideo = findViewById(R.id.etSearchVideo);
        etSearchVideo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sEbook(etSearchVideo.getText().toString(),kelasuser,sekolahuser,"2");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void refresh(String kelas, String sekolah, String tipe) {
        Call<File> ebookCall = mApiInterface.getFile(kelas,sekolah,tipe);
        ebookCall.enqueue(new Callback<File>() {
            @Override
            public void onResponse(Call<File> call, Response<File> response) {
                List<FileData> EbookList = response.body().getFileData();
                Log.d("Retrofit Get", "Jumlah data Tugas: " +
                        String.valueOf(EbookList.size()));
                mAdapter = new FileAdapter(EbookList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<File> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void sEbook(String judul,String kelas,String sekolah, String tipe){
        Call<File> callSearch = mApiInterface.searchFile(judul,kelas,sekolah,tipe);
        callSearch.enqueue(new Callback<File>() {
            @Override
            public void onResponse(Call<File> call, Response<File> response) {
                List<FileData> EbookList = response.body().getFileData();
                Log.d("Retrofit Get", "Jumlah data Tugas: " +
                        String.valueOf(EbookList.size()));
                mAdapter = new FileAdapter(EbookList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<File> call, Throwable t) {
                Toast.makeText(VideoActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}