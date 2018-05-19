package com.sonhoai.sonho.walletem;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by sonho on 3/19/2018.
 */

public class MyInstanceIdListenerService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        //Toast.makeText(getApplicationContext(), refreshToken,Toast.LENGTH_SHORT).show();
        Log.i("FCMAPP", "Token is "+refreshToken);
    }
}
