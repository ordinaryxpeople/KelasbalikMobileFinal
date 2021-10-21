package com.kelasbalik.kelasbalikmobile.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelasbalik.kelasbalikmobile.Config.SessionManager;
import com.kelasbalik.kelasbalikmobile.LoginActivity;
import com.kelasbalik.kelasbalikmobile.R;
import com.ornach.nobobutton.NoboButton;

import java.io.InputStream;

public class ProfilFragment extends Fragment {

    SessionManager sessionManager;
    NoboButton buttonLogout;


    public ProfilFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(view.getContext());
        String urlfoto = sessionManager.getUserDetail().get(SessionManager.FOTOUSER);
        String nisn = sessionManager.getUserDetail().get(SessionManager.KODEUSER);
        String namaprofil = sessionManager.getUserDetail().get(SessionManager.NAMAUSER);
        String emailprofil = sessionManager.getUserDetail().get(SessionManager.EMAILUSER);
        String alamatprofil = sessionManager.getUserDetail().get(SessionManager.ALAMATUSER);
        String notelpprofil = sessionManager.getUserDetail().get(SessionManager.NOTELPUSER);
        String tlahirprofil = sessionManager.getUserDetail().get(SessionManager.TLAHIRUSER);
        String tgllahirprofil = sessionManager.getUserDetail().get(SessionManager.TGLLAHIRUSER);
        String kelasprofil = sessionManager.getUserDetail().get(SessionManager.IDKELASUSER);

//        TextView tvKelas = (TextView) view.findViewById(R.id.tvKelasUserProfil);
        TextView tvnama = (TextView) view.findViewById(R.id.tvNamaProfil);
        TextView tvnisn = (TextView) view.findViewById(R.id.tvNisnProfil);
        TextView tvemail = (TextView) view.findViewById(R.id.tvEmailUserProfil);
        TextView tvalamat = (TextView) view.findViewById(R.id.tvAlamatUserProfil);
        TextView tvnotelp = (TextView) view.findViewById(R.id.tvNotelpUserProfil);
        TextView tvttl = (TextView) view.findViewById(R.id.tvTTLUserProfil);
//        TextView tvTotaltugas = (TextView) view.findViewById(R.id.tvTugasUserProfil);

//        tvKelas.setText(kelasprofil);
        tvnama.setText(namaprofil);
        tvnisn.setText(nisn);
        tvemail.setText(emailprofil);
        tvalamat.setText(alamatprofil);
        tvnotelp.setText(notelpprofil);
        tvttl.setText(tlahirprofil+", "+tgllahirprofil);
        ImageView imageViewProfil = (ImageView) view.findViewById(R.id.ivFotoProfil);
        new DownloadImageProfilFromInternet(imageViewProfil).execute("https://admin.kelasbalik.id/assets/media/users/"+urlfoto);

        buttonLogout = (NoboButton) view.findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logoutSession();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

class DownloadImageProfilFromInternet extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    public DownloadImageProfilFromInternet(ImageView imageView) {
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