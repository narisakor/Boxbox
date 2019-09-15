package com.workshop.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.workshop.myapplication.model.Box;
import com.workshop.myapplication.services.RequestAPI;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class InputOutActivity extends AppCompatActivity {

    Button btnInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choose_input_out);

        initView();
        setAction();
    }

    private void initView() {
        //TODO binding view to Activity
        btnInput = (Button) findViewById(R.id.btn_input);
    }

    private void setAction() {
        //TODO set event
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputOutActivity.this
                        , BoxListActivity.class);
                startActivity(intent);
            }
        });
    }


}
