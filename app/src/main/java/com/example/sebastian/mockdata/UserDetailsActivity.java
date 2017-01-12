package com.example.sebastian.mockdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.sebastian.mockdata.model.User;

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

    private User user;


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
        user = bundle.getParcelable("user");
        name.setText(user.getName());
        lastName.setText(user.getLastName());
        country.setText(user.getCountry());
    }
}
