package com.example.pctruong;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PCTruong on 18/06/2018.
 */

public class ManagerSharedPreference {

   static SharedPreferences pref;

    static   SharedPreferences.Editor editor;
    Context _context;
    static  int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "AppBanHang";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    public static final String KEY_UID = "KEY_UID";
    public static final String KEY_NAME = "KEY_NAME";

    public ManagerSharedPreference (Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setNameUID(String name ,String uid){
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_UID,uid);
        editor.commit();
    }
    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();

    }

    public  boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public static  void deleteByKey(){
       editor.clear().commit();
    }
    public static String getName(Context context ,String name){
        pref=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        return pref.getString(name,"");
    }

    public static String getUID(Context context ,String uid){
        pref=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        return pref.getString(uid,"");
    }
}
