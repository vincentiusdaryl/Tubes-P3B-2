package com.pppb.if_apps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.pppb.if_apps.Model.Key;
import com.pppb.if_apps.Presenter.LoginPresenter;
import com.pppb.if_apps.databinding.ActivityMainLoginBinding;

public class MainActivityLogin extends AppCompatActivity implements View.OnClickListener {

    ActivityMainLoginBinding binding;
    Button button1;
    Button button2;
    Button button3;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        this.button1 = findViewById(R.id.btn_admin);
        this.button1.setOnClickListener(this);
        this.button2 = findViewById(R.id.btn_dosen);
        this.button2.setOnClickListener(this);
        this.button3 = findViewById(R.id.btn_mhs);
        this.button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == this.button1){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.d("debug","clicked" );
        }
        else if(view == this.button2){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.d("debug","clicked" );
        }
        else if(view == this.button3){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.d("debug","clicked" );
        }
    }
}
