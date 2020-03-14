package com.example.a_remin;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.UUID;

public class arm_list extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arm_list);
        final Button mBtnBack = (Button)findViewById(R.id.btnBack);
        final Button mBtnStart = (Button)findViewById(R.id.btnStart);
        final Button mBtnStop = findViewById(R.id.btnStop);



        final EditText mTxtPlus = findViewById(R.id.txtpPlusAngle);
        final EditText mTxtPlus_stat = findViewById(R.id.txtpPlusAngle_stat);
        final EditText mTxtMinus = findViewById(R.id.txtMinusAngle);
        final EditText mTxtMinus_stat = findViewById(R.id.txtMinusAngle_stat);
        final EditText mTxtQuantity = findViewById(R.id.txtQuantity);
        final EditText txtMin =  findViewById(R.id.txtpMin);
        final EditText txtSec = findViewById(R.id.txtSec);
        //myThreadConnected.run();
        txtMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String number = txtMin.getText().toString();
                if (number.length()!= 0) {
                    int number_int = Integer.parseInt(number);
                    if (number_int < 1) {
                        txtMin.setText("0");
                        Toast.makeText(arm_list.this, "Min < 0", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtSec.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String number = txtSec.getText().toString();
                if (number.length()!= 0) {
                    int number_int = Integer.parseInt(number);
                    if (number_int > 60) {
                        txtSec.setText("59");
                        Toast.makeText(arm_list.this, "Секунд <=60", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }

        });
        final byte[] pack;
        pack = new byte[8];

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.stop_byte((byte) 1);
            }
        });
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int angle_plus = 0;
                if(mTxtPlus.getText().length() > 0) {
                    angle_plus = Integer.parseInt(mTxtPlus.getText().toString());
                }
                int angle_minus = 0;
                if(mTxtMinus.getText().length() > 0) {
                    angle_minus = Integer.parseInt(mTxtMinus.getText().toString());
                }
                int angle_plus_stat = 0;
                if(mTxtPlus_stat.getText().length() > 0) {
                    angle_plus_stat = Integer.parseInt(mTxtPlus_stat.getText().toString());
                }
                int angle_minus_stat = 0;
                if(mTxtMinus_stat.getText().length() > 0) {
                    angle_minus_stat = Integer.parseInt(mTxtMinus_stat.getText().toString());
                }
                int quantity =0;
                if(mTxtQuantity.getText().length() > 0) {
                    quantity = Integer.parseInt(mTxtQuantity.getText().toString());
                }
                int min = 0;
                int sec = 0;
                if(txtMin.getText().length() > 0){
                    min =  Integer.parseInt(txtMin.getText().toString());
                }
                if(txtMin.getText().length() > 0){
                    min =  Integer.parseInt(txtMin.getText().toString());
                }
                if(txtSec.getText().length() > 0){
                    sec =  Integer.parseInt(txtSec.getText().toString());
                }
                //int sec =  Integer.parseInt(txtSec.getText().toString());
                //int min = Integer.parseInt(txtMin.getText().toString());
                //int sec = Integer.parseInt(txtSec.getText().toString());
                pack[0]= (byte) angle_plus;
                pack[1] = (byte) angle_minus;
                pack[2] = (byte) quantity;
                //int a = 1;
                pack[3] = (byte)angle_plus_stat;
                pack[4] = (byte)angle_minus_stat;
                pack[5] = (byte)min;
                pack[6] = (byte)sec;
                pack[7] = 1;
                MainActivity.check(pack);

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Send", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

    }

    public static byte[] floatToByteArray(float value) {
        int intBits =  Float.floatToIntBits(value);
        return new byte[] {
                (byte) (intBits >> 24), (byte) (intBits >> 16), (byte) (intBits >> 8), (byte) (intBits) };
    }



    public void showToast(String text){
        Toast toast = Toast.makeText(getApplicationContext(),
                text, Toast.LENGTH_SHORT);
        toast.show();
    }
}




