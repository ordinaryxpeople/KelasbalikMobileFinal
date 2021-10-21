package com.kelasbalik.kelasbalikmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.kelasbalik.kelasbalikmobile.Adapter.Tugas.TugasAdapter;
import com.kelasbalik.kelasbalikmobile.Config.SessionManager;
import com.kelasbalik.kelasbalikmobile.Fragment.HomeFragment;
import com.kelasbalik.kelasbalikmobile.Fragment.ProfilFragment;
import com.kelasbalik.kelasbalikmobile.Fragment.TugasFragment;
import com.kelasbalik.kelasbalikmobile.Model.OpenSignal.OpenSignal;
import com.kelasbalik.kelasbalikmobile.Model.Tugas.Tugas;
import com.kelasbalik.kelasbalikmobile.Model.Tugas.TugasData;
import com.kelasbalik.kelasbalikmobile.Rest.ApiClient;
import com.kelasbalik.kelasbalikmobile.Rest.ApiInterface;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    SessionManager sessionManager;
    SmoothBottomBar bottomBar;
    private static final String ONESIGNAL_APP_ID = "d3061a74-12fa-4797-8057-fdb66864fd95";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        sessionManager = new SessionManager(MainActivity.this);
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        String nisn = sessionManager.getUserDetail().get(SessionManager.KODEUSER);
        String iduser = sessionManager.getUserDetail().get(SessionManager.IDUSER);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.setExternalUserId(nisn, new OneSignal.OSExternalUserIdUpdateCompletionHandler() {
            @Override
            public void onSuccess(JSONObject results) {
                OneSignal.onesignalLog(OneSignal.LOG_LEVEL.VERBOSE, "Set external user id done with results: " + results.toString());
                try {
                    if (results.has("push") && results.getJSONObject("push").has("success")) {
                        boolean isPushSuccess = results.getJSONObject("push").getBoolean("success");

                        OSDeviceState deviceState = OneSignal.getDeviceState();
                        String userId = deviceState != null ? deviceState.getUserId() : null;
                        refresh(userId,iduser);
                        OneSignal.onesignalLog(OneSignal.LOG_LEVEL.VERBOSE, "Set external user id for push status: " + isPushSuccess + " iki id bro : "+userId);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(OneSignal.ExternalIdError error) {
                OneSignal.onesignalLog(OneSignal.LOG_LEVEL.VERBOSE, "Set external user id done with error: " + error.toString());
            }
        });
        setContentView(R.layout.activity_main);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }else{
            bottomBar = findViewById(R.id.bnvMain);
            rFragment(new HomeFragment());
            bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public boolean onItemSelect(int i) {
                    switch (i){
                        case 0:
                            rFragment(new HomeFragment());
                            break;
                        case 1:
                            rFragment(new TugasFragment());
                            break;
                        case 2:
                            rFragment(new ProfilFragment());
                            break;
                    }
                    return true;
                }
            });
        }
    }

    public void refresh(String iddevice,String iduser) {
        Call<OpenSignal> signalCall = mApiInterface.postOSID(iddevice,iduser);
        signalCall.enqueue(new Callback<OpenSignal>() {
            @Override
            public void onResponse(Call<OpenSignal> call, Response<OpenSignal> response) {
                Log.v("Respon signal", "Sukses!");
            }

            @Override
            public void onFailure(Call<OpenSignal> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    private void rFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frMain,fragment);
        fragmentTransaction.commit();
    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}