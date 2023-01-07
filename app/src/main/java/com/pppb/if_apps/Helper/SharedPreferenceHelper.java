package com.pppb.if_apps.Helper;

import com.pppb.if_apps.Model.Key;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {

    private static final String PREFERENCE_FILE_KEY = "PREFERENCE_FILE_KEY";

    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private SharedPreferences.Editor editor ;

    public SharedPreferenceHelper(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

        public void saveString(String key, String value) {
            mSharedPreferences.edit().putString(key, value).apply();
        }

        public String getString(String key) {
            return mSharedPreferences.getString(key, null);
        }

        public void saveInt(String key, int value) {
            mSharedPreferences.edit().putInt(key, value).apply();
        }

        public int getInt(String key) {
            return mSharedPreferences.getInt(key, 0);
        }

        public void saveBoolean(String key, boolean value) {
            mSharedPreferences.edit().putBoolean(key, value).apply();
        }

        public boolean getBoolean(String key) {
            return mSharedPreferences.getBoolean(key, false);
        }

        public void clear(){
            editor.clear()
            .apply();
        }

}
