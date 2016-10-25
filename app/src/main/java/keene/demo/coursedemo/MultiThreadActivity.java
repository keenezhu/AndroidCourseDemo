package keene.demo.coursedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;



public class MultiThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button uiReceive, uiSend;

    private WorkerThread wt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        uiReceive = (Button) findViewById(R.id.ui_receive);
        uiSend = (Button) findViewById(R.id.ui_send);
        uiSend.setOnClickListener(this);
        uiReceive.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        wt=new WorkerThread();
        wt.start();

    }

    int clickCount = 0;

    @Override
    public void onClick(View v) {
        clickCount++;
        int id = v.getId();
        if (id == R.id.ui_send) {
            Message msg = workerHandler.obtainMessage();
            msg.arg1 = clickCount;
            workerHandler.sendMessage(msg);
        } else if (id == R.id.ui_receive) {
            wt.work();
        }
    }

    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            uiReceive.setText(String.valueOf(msg.arg1));
        }
    };

    Handler workerHandler;

    class WorkerThread extends Thread {
        @Override
        public void run() {
            Looper.prepare();
            workerHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    final String text = String.valueOf(msg.arg1);
                    uiSend.post(new Runnable() {
                        @Override
                        public void run() {
                            uiSend.setText(text);
                        }
                    });
                }
            };
            Looper.loop();
        }

        public void work() {
            Message msg = uiHandler.obtainMessage();
            msg.arg1 = 1000;
            uiHandler.sendMessage(msg);
        }

    }


}
