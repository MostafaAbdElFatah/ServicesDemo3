package com.mostafaabdel_fatah.servicesdemo3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Mostafa AbdEl_Fatah on 9/4/2017.
 */

public class MyService extends Service {

    private final IBinder iBinder = new LocalService();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public class LocalService extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public String getFirstMessage(){
        return "Welcome all in  world ";
    }

    public String getSecondMessage(){
        return "this is Bound Service Example.......";
    }

}
