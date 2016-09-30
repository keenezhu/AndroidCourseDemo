package keene.demo.coursedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class FirstStartedService extends Service {

    private CustomHanlder ch;

    public FirstStartedService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("service created:","flag");
        HandlerThread ht=new HandlerThread("handler.thread.name");
        ht.start();
        ch=new CustomHanlder(ht.getLooper());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("service started:","flag");
        Log.w("main thread id:",""+Thread.currentThread().getName()+Thread.currentThread().getId());
        //do service task;
        //stopSelf();
        ch.sendEmptyMessage(0);
        return Service.START_STICKY;
    }

    private class CustomHanlder extends Handler{
        public CustomHanlder(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.w("handler thread id:",""+Thread.currentThread().getName()+Thread.currentThread().getId());
        }
    }


}
