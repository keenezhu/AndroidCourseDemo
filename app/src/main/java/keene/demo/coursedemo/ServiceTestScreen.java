package keene.demo.coursedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ServiceTestScreen extends AppCompatActivity {

    private TextView tv;

    public ServiceTestScreen() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test_screen);
        tv = (TextView) findViewById(R.id.reply_text);
    }

    public void onClickButton(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.start_service: {
                Intent serviceIntent = new Intent(this, FirstStartedService.class);
                startService(serviceIntent);
                break;
            }
            case R.id.bind_service: {
                Intent serviceIntent = new Intent(this, FirstBinderService.class);
                bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            }
            case R.id.start_intent_service: {
                Intent serviceIntent = new Intent(this, FirstIntentService.class);
                startService(serviceIntent);
                break;
            }
            case R.id.send_msg: {
                if (serviceBound) {
                    if (serverMsger != null) {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("send", "client msg");
                        msg.setData(data);
                        msg.replyTo = clientMsger;
                        try {
                            serverMsger.send(msg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
        }
    }

    private boolean serviceBound = false;
    private Messenger serverMsger;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.w("component name:", name.getClassName());
            serverMsger = new Messenger(binder);
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serverMsger = null;
            serviceBound = false;
        }
    };
    private Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv.setText(msg.getData().getString("reply"));
        }
    };

    private Messenger clientMsger = new Messenger(h);

    @Override
    protected void onStop() {
        super.onStop();
        if (serviceBound) {
            unbindService(serviceConnection);
            serviceBound = false;
            serverMsger = null;
        }
    }
}
