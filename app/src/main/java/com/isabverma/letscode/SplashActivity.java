
package com.isabverma.letscode;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.isabverma.letscode.intro.MainIntroActivity;

public class SplashActivity extends Activity {

    Thread timerThread;
    PrefManager prefManager;
    Intent intent;
    boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        firstTime = prefManager.isFirstTimeLaunch();

        setContentView(R.layout.activity_splash);

        timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    if (!firstTime) {
                        intent = new Intent(SplashActivity.this,MainActivity.class);
                    }else{
                        //prefManager.setFirstTimeLaunch(false);
                        intent = new Intent(SplashActivity.this,MainIntroActivity.class);
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }
}

