package com.example.sebastian.mockdata;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sebastian.mockdata.adapters.DataAdapter;
import com.example.sebastian.mockdata.model.JSONMockData;
import com.example.sebastian.mockdata.model.User;
import com.example.sebastian.mockdata.service.HTTPRequestHandler;
import com.example.sebastian.mockdata.support.ItemClickSupport;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.toString();

    public static final String JSON_URL = "http://www.mocky.io/v2/58628ab00f0000350e175575";

    @BindView(R.id.downloadBtn)
    Button downloadBtn;
    @BindView(R.id.title)
    TextView title;
    private User user;
    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private HTTPRequestHandler httpRequestHandler;
    private String jsonTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeRecyclerView();
        httpRequestHandler = HTTPRequestHandler.getInstance();
        httpRequestHandler.init(getApplicationContext());
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpRequestHandler.sendGetRequest(JSON_URL, getResponseListener(), getErrorListener());
            }
        });
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                getUser(position);
                goToDataActivity(user);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("list")) {
            userList = savedInstanceState.getParcelableArrayList("list");
            jsonTitle = savedInstanceState.getString("title");
            initializeView(userList, jsonTitle);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) userList);
        outState.putString("title", jsonTitle);
    }


    public void initializeRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private Response.Listener<String> getResponseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        };
    }

    private Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT);
            }
        };
    }

    private void showJSON(String json) {
        JSONMockData jsonMockData = new JSONMockData();
        jsonMockData.initializeFromJson(json);
        userList = jsonMockData.getUserList();
        jsonTitle = jsonMockData.getTitle();
        initializeView(userList, jsonTitle);
    }

    public void initializeView(List<User> userList, String jsonTitle) {
        dataAdapter = new DataAdapter(userList);
        recyclerView.setAdapter(dataAdapter);
        title.setText(jsonTitle);
    }

    public void getUser(int position){
      user = userList.get(position);
    }

    public void goToDataActivity(User user) {
        Intent userDetailsIntent = new Intent(getApplicationContext(), UserDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable("user", user);
        userDetailsIntent.putExtras(extras);
        startActivity(userDetailsIntent);
    }
}