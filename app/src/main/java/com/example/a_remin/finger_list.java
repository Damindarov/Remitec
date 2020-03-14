package com.example.a_remin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class finger_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_list);
        Button mBtnBack = findViewById(R.id.btnBack);
        final Button mBtnStart = findViewById(R.id.btnStart);
        final Button mBtnStop = findViewById(R.id.btnStop);
        mBtnStop.setEnabled(false);

        final EditText mTxtPlus = findViewById(R.id.txtpPlusAngle);
        final EditText mTxtMinus = findViewById(R.id.txtMinusAngle);
        final EditText mTxtQuantity = findViewById(R.id.txtQuantity);
        final byte[] pack;
        pack = new byte[8];
        //mTxtMinus.setText("0");
        //mTxtPlus.setText("0");
        //mTxtQuantity.setText("0");
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
                int angle_minus = 0;
                int quantity =  Integer.parseInt(mTxtQuantity.getText().toString());
                pack[0]= (byte) angle_plus;
                pack[1] = (byte) angle_minus;
                pack[2] = (byte) quantity;
                int a = 3;
                pack[7] = (byte)a;//type
                MainActivity.check(pack);

                mBtnStop.setEnabled(true);
                mBtnStart.setEnabled(false);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Send", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

    }
}
