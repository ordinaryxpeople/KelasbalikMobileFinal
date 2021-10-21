package com.kelasbalik.kelasbalikmobile.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelasbalik.kelasbalikmobile.Adapter.Tugas.TugasAdapter;
import com.kelasbalik.kelasbalikmobile.Config.SessionManager;
import com.kelasbalik.kelasbalikmobile.Model.Tugas.Tugas;
import com.kelasbalik.kelasbalikmobile.Model.Tugas.TugasData;
import com.kelasbalik.kelasbalikmobile.R;
import com.kelasbalik.kelasbalikmobile.Rest.ApiClient;
import com.kelasbalik.kelasbalikmobile.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TugasFragment extends Fragment {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static TugasFragment tugasFragment;
    SessionManager sessionManager;

    public TugasFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tugas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(view.getContext());
        String kelasuser = sessionManager.getUserDetail().get(SessionManager.IDKELASUSER);
        String sekolahuser = sessionManager.getUserDetail().get(SessionManager.IDSEKOLAHUSER);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvTugasTugas);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        tugasFragment=this;
        refresh(kelasuser,sekolahuser);
    }

    public void refresh(String kelas, String sekolah) {
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