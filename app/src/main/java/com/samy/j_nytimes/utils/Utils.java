package com.samy.j_nytimes.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Window;
import android.view.WindowManager;

public class Utils {

    public static void statusBarColor(Activity activity){
        Window window=activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#9688FF"));

    }
}
