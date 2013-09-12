package com.nibdev.otrta2;

import android.app.Application;

import com.nibdev.otrtav2.model.Statics;

public class OTRTApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Statics.RefContext = this;
    }
}
