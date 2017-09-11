package com.mostafaabdel_fatah.servicesdemo3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MyService myService;
    Boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        Intent intent = new Intent(MainActivity.this,MyService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void firstMessage_btnClicked(View view) {
        textView.setText(myService.getFirstMessage());
    }

    public void secondMessage_btnClicked(View view) {
        textView.setText(myService.getSecondMessage());
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.LocalService localService = (MyService.LocalService)iBinder;
            myService = localService.getService();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (isBind){
            unbindService(serviceConnection);
            isBind = false;
        }
    }
}
