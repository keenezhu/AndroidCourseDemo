package keene.demo.coursedemo;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;


public class FirstIntentService extends IntentService {


    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("main thread id:",""+Thread.currentThread().getName()+Thread.currentThread().getId());
    }

    public FirstIntentService() {
        super("IntentServiceName");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.w("worker thread id:",""+Thread.currentThread().getName()+Thread.currentThread().getId());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
