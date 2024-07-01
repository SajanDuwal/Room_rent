package com.sajiman.myroomrent.Utils;

import android.util.Log;

import com.sajiman.myroomrent.BuildConfig;

public class AppLogUtils {

    public static void showLog(String classTag, String message) {
        if (!BuildConfig.BUILD_TYPE.equals("release")) {
            Log.e("APP_NAME : RENT_ROOM ", classTag + " ::: " + message);
        }
    }
}