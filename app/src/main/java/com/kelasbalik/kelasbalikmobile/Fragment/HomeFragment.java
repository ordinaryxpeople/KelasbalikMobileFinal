package com.kelasbalik.kelasbalikmobile.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelasbalik.kelasbalikmobile.Adapter.Tugas.TugasAdapter;
import com.kelasbalik.kelasbalikmobile.Config.SessionManager;
import com.kelasbalik.kelasbalikmobile.EbookActivity;
import com.kelasbalik.kelasbalikmobile.Model.Tugas.Tugas;
import com.kelasbalik.kelasbalikmobile.Model.Tugas.TugasData;
import com.kelasbalik.kelasbalikmobile.ModulActivity;
import com.kelasbalik.kelasbalikmobile.R;
import com.kelasbalik.kelasbalikmobile.Rest.ApiClient;
import com.kelasbalik.kelasbalikmobile.Rest.ApiInterface;
import com.kelasbalik.kelasbalikmobile.VideoActivity;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static HomeFragment hf;
    SessionManager sessionManager;
    TextView tvNamaProfilHome;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageViewProfil = (ImageView) view.findViewById(R.id.ivCVProfilHome);
        sessionManager = new SessionManager(view.getContext());
        String urlfoto = sessionManager.getUserDetail().get(SessionManager.FOTOUSER);
        new DownloadImageFromInternet(imageViewProfil).execute("https://admin.kelasbalik.id/assets/media/users/"+urlfoto);
        String kelasuser = sessionManager.getUserDetail().get(SessionManager.IDKELASUSER);
        String kelas = sessionManager.getUserDetail().get(SessionManager.KELASUSER);
        String sekolahuser = sessionManager.getUserDetail().get(SessionManager.IDSEKOLAHUSER);
        TextView tvGreeetingsHome = (TextView) view.findViewById(R.id.tvGreeetingsHome);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            tvGreeetingsHome.setText("Selamat Pagi");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            tvGreeetingsHome.setText("Selamat Siang");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            tvGreeetingsHome.setText("Selamat Sore");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            tvGreeetingsHome.setText("Selamat Malam");
        }

        tvNamaProfilHome = (TextView) view.findViewById(R.id.tvNamaProfilHome);
        String namauser = sessionManager.getUserDetail().get(SessionManager.NAMAUSER);
        TextView tvTugasKelas = (TextView) view.findViewById(R.id.tvTugasKelas);
        tvTugasKelas.setText("Kelas : "+kelas);
        tvNamaProfilHome.setText(namauser);

        CardView btnEbook = view.findViewById(R.id.cvBtnEbookHome);
        btnEbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EbookActivity.class);
                startActivity(intent);
            }
        });

        CardView btnModul = view.findViewById(R.id.cvBtnModulHome);
        btnModul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ModulActivity.class);
                startActivity(intent);
            }
        });
        CardView btnVideo = view.findViewById(R.id.cvBtnVideoHome);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), VideoActivity.class);
                startActivity(intent);
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvTugasHome);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        hf=this;
        refresh(kelasuser,sekolahuser);
    }

    public void refresh(String kelas,String sekolah) {
        Call<Tugas> tugasCall = mApiInterface.tugasResponse(kelas,sekolah);
        tugasCall.enqueue(new Callback<Tugas>() {
            @Override
            public void onResponse(Call<Tugas> call, Response<Tugas> response) {
                List<TugasData> TugasList = response.body().getTugasData();
                Log.d("Retrofit Get", "Jumlah data Tugas: " +
                        String.valueOf(TugasList.size()));
                mAdapter = new TugasAdapter(TugasList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Tugas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    public DownloadImageFromInternet(ImageView imageView) {
        this.imageView=imageView;
    }
    protected Bitmap doInBackground(String... urls) {
        String imageURL=urls[0];
        Bitmap bimage=null;
        try {
            InputStream in=new java.net.URL(imageURL).openStream();
            bimage= BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}