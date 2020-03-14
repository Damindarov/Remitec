package com.example.a_remin;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class static_list extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_list);
        Button mBtnBack = findViewById(R.id.btnBack);
        final Button mBtnStart = findViewById(R.id.btnStart);
        final Button mBtnStop = findViewById(R.id.btnStop);
        final EditText txtSec = findViewById(R.id.txtSec);
        final EditText txtMin = findViewById(R.id.txtMin);
        final EditText mTxtPlus = findViewById(R.id.txtpPlusAngle);
        final EditText mTxtMinus = findViewById(R.id.txtMinusAngle);
        final byte[] pack;
        pack = new byte[6];
        //txtMin.setText("0");
        txtMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String number = txtMin.getText().toString();
                if (number.length()!= 0) {
                    int number_int = Integer.parseInt(number);
                    if (number_int < 1) {
                        txtMin.setText("0");
                        Toast.makeText(static_list.this, "Min < 0", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(static_list.this, "Секунд <=60", Toast.LENGTH_SHORT).show();
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
                mBtnStart.setEnabled(true);
                mBtnStop.setEnabled(false);
            }
        });
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int angle_plus =  Integer.parseInt(mTxtPlus.getText().toString());
                int angle_minus =  Integer.parseInt(mTxtMinus.getText().toString());
                int min = 0;
                if(txtMin.getText().length() > 0){
                    min =  Integer.parseInt(txtMin.getText().toString());
                }

                int sec =  Integer.parseInt(txtSec.getText().toString());

                pack[0]= (byte) angle_plus;
                pack[1] = (byte) angle_minus;
                pack[2] = (byte) 0;
                int a = 2;
                pack[3] = (byte)a;//type

                pack[4] = (byte)min;
                pack[5] = (byte)sec;
                MainActivity.check(pack);

                mBtnStop.setEnabled(true);
                mBtnStart.setEnabled(false);


            }
        });



    }
}
