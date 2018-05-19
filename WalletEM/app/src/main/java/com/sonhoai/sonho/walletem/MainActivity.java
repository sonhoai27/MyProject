package com.sonhoai.sonho.walletem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.androidhive.barcode.BarcodeReader;

public class MainActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener{

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference mNotificationDatabase, mUserDatabase;
    private BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intFB();
        //signUp("kimsonnie.272@gmail.com", "123456789");
        //signIn("kimsonnie.272@gmail.com", "123456789");
        //signIn("sonhoai272@gmail.com", "123456789");
        signIn("kimsonnie.27@gmail.com", "123456789");
    }


    private void intFB(){
        firebaseAuth = FirebaseAuth.getInstance();
        mNotificationDatabase = FirebaseDatabase.getInstance().getReference().child("wallets");
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        barcodeReader = (BarcodeReader)getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }
    //ktra co dang nhap chua
    private FirebaseAuth.AuthStateListener fbListener(){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //success
                    Toast.makeText(getApplicationContext(),user.getEmail(), Toast.LENGTH_SHORT).show();
                }else {
                    //error
                    Toast.makeText(getApplicationContext(),"Chưa có", Toast.LENGTH_SHORT).show();
                }
            }
        };

        return authStateListener;
    }

    //signup
    private void signUp(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "That bai",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "That bai",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void signIn(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String token = FirebaseInstanceId.getInstance().getToken();
                    Map<String, String> user = new HashMap<>();
                    user.put("token", token);
                    user.put("email", firebaseAuth.getCurrentUser().getEmail());
                    mUserDatabase.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                    //Toast.makeText(getApplicationContext(), "Thanh cong",Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(getApplicationContext(), "That bai",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(fbListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(fbListener());
        }
    }

    private void requestNotifyToPayment(String data){
        try {
            JSONObject objInfo = new JSONObject(data);
            String senderUid = objInfo.getString("from");
           if(objInfo.getString("status").equals("0") && objInfo.getString("message").equals("SCAN_QRCODE")){
               HashMap<String, String> notificationData = new HashMap<>();
               notificationData.put("from", senderUid);
               notificationData.put("message", "XAC_NHAN");
               notificationData.put("status", "1");
               notificationData.put("id", objInfo.getString("id"));
               mNotificationDatabase.child(senderUid+"/"+objInfo.getString("id")).setValue(notificationData);
               requestListen(objInfo.getString("id"));
           }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void requestListen(final String keyNode){
        mNotificationDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Wallet wallet = dataSnapshot.child(keyNode).getValue(Wallet.class);
                Log.i("STATUS_NODE", dataSnapshot.child(keyNode).getValue(Wallet.class).toString());
                if(wallet.getMessage().equals("THANH_CONG") && wallet.getStatus().equals("2")){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("REQUEST", "THANH_CONG");
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onScanned(Barcode barcode) {
        barcodeReader.playBeep();
        byte[] data = Base64.decode(barcode.displayValue, Base64.DEFAULT);
        try {
            Log.i("KAKA", new String(data, "UTF-8"));
            String response = new String(data, "UTF-8");
            requestNotifyToPayment(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {

    }
}
