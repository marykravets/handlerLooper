package com.example.testapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

// thread that gets messages from Activity
public class BackgroundThread extends Thread implements Handler.Callback {

    private static final String TAG = "testTag1 bgThread";

    @Override
    public void run() {
        Looper.prepare();

        //looper for current Thread.
        Looper myLooper = Looper.myLooper();
        App.testMessageBgHandler = new Handler(myLooper, this);

        Looper.loop();
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        Bundle bundle = msg.getData();

        Log.d(TAG, "Handler:: Extras: ${" + bundle + "}");
        Log.d(TAG, "Handler:: Background Thread ID ${" + Thread.currentThread().getId() + "}");
        Log.d(TAG, "Handler:: Background handler ${" + App.testMessageBgHandler.toString() + "}");

        return true;
    }
}