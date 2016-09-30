package keene.demo.coursedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class FirstBinderService extends Service {

    private final Messenger serverMsger = new Messenger(new MessageHandler());

    public FirstBinderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return serverMsger.getBinder();
    }

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Message replyMsg = new Message();
            Bundle data = new Bundle();
            data.putString("reply", msg.getData().getString("send") + "--server msg");
            replyMsg.setData(data);
            try {
                msg.replyTo.send(replyMsg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
