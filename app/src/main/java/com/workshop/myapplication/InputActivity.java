package com.workshop.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.workshop.myapplication.model.Result;
import com.workshop.myapplication.services.RequestAPI;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class InputActivity extends AppCompatActivity {
    Button btnInput;
    EditText edtRoomNo;
    String boxId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_input);

        boxId = getIntent().getExtras().getString("box_id", "");

        initView();
        setAction();


    }

    private void initView() {
        //TODO binding view to Activity
        btnInput = (Button) findViewById(R.id.btn_ok);
        edtRoomNo = (EditText) findViewById(R.id.edt_room_no);
    }

    private void setAction() {
        //TODO set event
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CallAPI().execute();
            }
        });
    }

    private class CallAPI extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //TODO start request to api
            progressDialog = new ProgressDialog(InputActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Progress Dialog");

            //To show the dialog
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            //TODO call api
            RequestAPI http = new RequestAPI();
            RequestBody formBody = new FormBody.Builder()
                    .add("box_id", boxId)
                    .add("room_no", edtRoomNo.getText().toString())
                    .build();
            String response = null;
            try {
                response = http.postForm("http://testok.kimhun55.com/cool/box/API/Inputbox.php", formBody);
                Log.d("Response", response);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //TODO after request to api
            progressDialog.dismiss();

            Result result1 = new Gson().fromJson(result, Result.class);

            if(result1.getStatus().equals("200")){
                Toast.makeText(InputActivity.this, "เรียบร้อย", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(InputActivity.this, "ไม่เรียบร้อย", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
