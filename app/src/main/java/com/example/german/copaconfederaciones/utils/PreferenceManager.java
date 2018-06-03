package com.example.german.copaconfederaciones.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by German on 3/6/2018.
 */

public class PreferenceManager {

    public static SharedPreferences.Editor edit(Context context){

        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .edit();

    }

    public static SharedPreferences get(Context context){

        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);

    }

}
