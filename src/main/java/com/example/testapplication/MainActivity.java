package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

// MainActivity sends a test message that's received in the BackgroundThread
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "testTag1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        BackgroundThread bgThread = new BackgroundThread();
        bgThread.start();
    }

    private void initUi() {
        Button btn = findViewById(R.id.test_btn);
        if (btn != null) {
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_btn:
            Log.d(TAG, "Handler:: Background Thread ID ${" + Thread.currentThread().getId() + "}");

                App.testMessageBgHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Handler:: Background Thread ID ${" + Thread.currentThread().getId() + "}");
                    }
                });

                setTestMessage();
                break;

            default: break;
        }
    }

    // sends test message to a handler that has looper
    private void setTestMessage() {
        Bundle extras = new Bundle();
        extras.putInt("value1", 100);
        extras.putString("value2", "test");

        Message message = Message.obtain(App.testMessageBgHandler);
        message.setData(extras);

        App.testMessageBgHandler.sendMessage(message);
    }
}
