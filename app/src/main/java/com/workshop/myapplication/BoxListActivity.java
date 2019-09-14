package com.workshop.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.workshop.myapplication.adapter.BoxAdapter;
import com.workshop.myapplication.adapter.ICustomItemClickedListener;
import com.workshop.myapplication.adapter.MarginDecoration;
import com.workshop.myapplication.model.Box;
import com.workshop.myapplication.services.RequestAPI;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class BoxListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BoxAdapter adapter;
    List<Box> boxes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_box);
        initView();
        setAdapter();

        new CallAPI().execute();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void setAdapter() {

        adapter = new BoxAdapter(this, boxes);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL , false));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MarginDecoration(this));
        adapter.addItemClickedListener(new ICustomItemClickedListener() {
            @Override
            public void onItemClicked(View v, int position) {
                String carId = boxes.get(position).getBoxId();

            }
        });


        adapter.addItemClickedListener(new ICustomItemClickedListener() {
            @Override
            public void onItemClicked(View v, int position) {

            }
        });


    }

    private class CallAPI extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //TODO start request to api
            progressDialog = new ProgressDialog(BoxListActivity.this);
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
                    .build();
            String response = null;
            try {
                response = http.postForm("http://testok.kimhun55.com/cool/box/API/GetboxList.php", formBody);
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
            Type listType = new TypeToken<List<Box>>() {
            }.getType();
            List<Box> boxesfromList = new Gson().fromJson(result, listType);
            boxes.addAll(boxesfromList);
            adapter.notifyDataSetChanged();
        }
    }
}


