package com.example.sebastian.mockdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.sebastian.mockdata.model.User;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends AppCompatActivity {

    private static String TAG = UserDetailsActivity.class.toString();

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.last_name)
    TextView lastName;
    @BindView(R.id.country)
    TextView country;

    private int position;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
        initializeView(getBundle());
    }

    private Bundle getBundle(){
        Intent intent = getIntent();
        return intent.getExtras();
    }

    private void initializeView(Bundle bundle){
        position = bundle.getInt("position");
        userList = bundle.getParcelableArrayList("userList");
        name.setText(userList.get(position).getName());
        lastName.setText(userList.get(position).getLastName());
        country.setText(userList.get(position).getCountry());
    }
}
