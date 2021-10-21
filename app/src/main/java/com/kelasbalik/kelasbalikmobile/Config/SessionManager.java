package com.kelasbalik.kelasbalikmobile.Config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.kelasbalik.kelasbalikmobile.Model.Login.LoginData;

import java.util.HashMap;

public class SessionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String IDUSER = "iduser";
    public static final String KODEUSER = "kode";
    public static final String NAMAUSER = "nama";
    public static final String EMAILUSER = "email";
    public static final String NOTELPUSER = "notelp";
    public static final String TLAHIRUSER = "tlahir";
    public static final String TGLLAHIRUSER = "tgllahir";
    public static final String ALAMATUSER = "alamat";
    public static final String ORTUUSER = "ortu";
    public static final String MOTTOUSER = "motto";
    public static final String FOTOUSER = "foto";
    public static final String IDSEKOLAHUSER = "idsekolah";
    public static final String IDKELASUSER = "idkelas";
    public static final String KELASUSER = "kelas";

    public SessionManager (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(IDUSER, user.getIduser());
        editor.putString(KODEUSER, user.getKode());
        editor.putString(NAMAUSER, user.getNama());
        editor.putString(EMAILUSER, user.getEmail());
        editor.putString(NOTELPUSER, user.getNotelp());
        editor.putString(TLAHIRUSER, user.getTlahir());
        editor.putString(TGLLAHIRUSER, user.getTgllahir());
        editor.putString(ALAMATUSER, user.getAlamat());
        editor.putString(ORTUUSER, user.getOrtu());
        editor.putString(MOTTOUSER, user.getMotto());
        editor.putString(FOTOUSER, user.getFoto());
        editor.putString(IDSEKOLAHUSER, user.getIdsekolah());
        editor.putString(IDKELASUSER, user.getIdkelas());
        editor.putString(KELASUSER, user.getKelas());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(IDUSER, sharedPreferences.getString(IDUSER,null));
        user.put(KODEUSER, sharedPreferences.getString(KODEUSER,null));
        user.put(NAMAUSER, sharedPreferences.getString(NAMAUSER,null));
        user.put(EMAILUSER, sharedPreferences.getString(EMAILUSER, null));
        user.put(NOTELPUSER, sharedPreferences.getString(NOTELPUSER, null));
        user.put(TLAHIRUSER, sharedPreferences.getString(TLAHIRUSER, null));
        user.put(TGLLAHIRUSER, sharedPreferences.getString(TGLLAHIRUSER, null));
        user.put(ALAMATUSER, sharedPreferences.getString(ALAMATUSER, null));
        user.put(ORTUUSER, sharedPreferences.getString(ORTUUSER, null));
        user.put(MOTTOUSER, sharedPreferences.getString(MOTTOUSER, null));
        user.put(FOTOUSER, sharedPreferences.getString(FOTOUSER, null));
        user.put(IDSEKOLAHUSER, sharedPreferences.getString(IDSEKOLAHUSER, null));
        user.put(IDKELASUSER, sharedPreferences.getString(IDKELASUSER, null));
        user.put(KELASUSER, sharedPreferences.getString(KELASUSER, null));
        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
