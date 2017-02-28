package com.dronesolutions.appgamarra.ui.utils;

import android.view.View;
import android.widget.Toast;

/**
 * Created by VANESSA on 28/01/2017.
 */
public class Generic {

    public static void showToast(View view, String message){
        Toast.makeText(view.getContext(),message, Toast.LENGTH_LONG).show();
    }
}
