package com.example.a_remin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choose_brush_mode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_brush_mode);
        Button mBtnBack = findViewById(R.id.btnBack);
        Button mBtnCpm = findViewById(R.id.btnCPM);
        Button mBtnStatic = findViewById(R.id.btnStatic);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBtnCpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose_brush_mode.this, arm_list.class);
                startActivity(intent);
            }
        });
        mBtnStatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose_brush_mode.this, static_list.class);
                startActivity(intent);
            }
        });
    }
}
