package com.example.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtfrstnum,edtscndnum;
    Button btnmultiply;
    TextView txtmultiply;
    MultiplyInterface myInterface;
    ServiceConnection myserviceconnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myInterface = MultiplyInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtfrstnum = findViewById(R.id.edtfirstnumber);
        edtscndnum = findViewById(R.id.edtsecondnumber);
        txtmultiply = findViewById(R.id.txtmultiplyresult);
        btnmultiply = findViewById(R.id.btnmultiply);
       btnmultiply.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int frstnum = Integer.parseInt(edtfrstnum.getText().toString());
               int scndnum = Integer.parseInt(edtscndnum.getText().toString());
               try {
                   int result = myInterface.multiplyTwoValues(frstnum,scndnum);
                   txtmultiply.setText(result + "");
               }catch (RemoteException e){
                   e.printStackTrace();
               }

           }
       });
        Intent multiplyIntent = new Intent(MainActivity.this,MultiplicationService.class);
       bindService(multiplyIntent,myserviceconnection, Context.BIND_AUTO_CREATE);
    }
}