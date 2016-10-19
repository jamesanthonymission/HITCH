package com.itproject.hitch_v1;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jamesz on 10/14/2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh(){

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//sendRegistrationToServer(refreshedToken);

        Log.d(TAG, "Refreshed token: " + refreshedToken);

//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
    }

    private void sendRegistrationToServer(String token){

    }

}
