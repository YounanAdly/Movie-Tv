package com.example.android.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by yuyu on 21-Nov-18.
 */

public class MainButtonChoose extends AppCompatActivity implements  View.OnClickListener {
    Button tv;
    Button movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_button);



        movie = findViewById(R.id.movie_button);
        tv = findViewById(R.id.tv_button);

        movie.setOnClickListener(this);
        tv.setOnClickListener( this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.movie_button:
                Intent movieIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(movieIntent);
                break;
            case R.id.tv_button:
                Intent tvIntent = new Intent(getBaseContext(),TvMain.class);
                startActivity(tvIntent);
                break;

        }
    }


}


