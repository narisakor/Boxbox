package com.workshop.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.workshop.myapplication.model.Box;
import com.workshop.myapplication.services.RequestAPI;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RequestAPI http = new RequestAPI();
//        RequestBody formBody = new FormBody.Builder()
//                .add("sName", "Weerachai")
//                .add("sLastName", "Nukitram")
//                .build();
//        String response = null;
//        try {
//            response = http.postForm("http://testok.kimhun55.com/cool/box/API/Getbox.php", formBody);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }


        new CallAPI().execute();
    }


    private class CallAPI extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //TODO start request to api
            progressDialog = new ProgressDialog(MainActivity.this);
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
                    .add("sName", "Weerachai")
                    .add("sLastName", "Nukitram")
                    .build();
            String response = null;
            try {
                response = http.postForm("http://testok.kimhun55.com/cool/box/API/Getbox.php", formBody);
                Log.d("Response", response);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //TODO after request to api
            //To dismiss the dialog
            progressDialog.dismiss();
            Type listType = new TypeToken<List<Box>>() {
            }.getType();
            List<Box> boxes = new Gson().fromJson(result, listType);
        }
    }
}
