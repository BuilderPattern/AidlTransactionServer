package com.sinitek.transactionserver;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.sinitek.aidl.IMathAidl;

public class MyService extends Service {
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Toast.makeText(getBaseContext(), "Service：" + msg.obj, Toast.LENGTH_SHORT).show();
            return false;
        }
    });
    private IMathAidl.Stub mStub = new IMathAidl.Stub() {
        @Override
        public double add(double a, double b) throws RemoteException {
            return a + b;
        }

        @Override
        public double sub(double a, double b) throws RemoteException {
            return a - b;
        }

        @Override
        public void play(String path) throws RemoteException {
            Message message = Message.obtain();
            message.obj = path;
            mHandler.sendMessage(message);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
