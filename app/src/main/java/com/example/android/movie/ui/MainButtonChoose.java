package com.example.android.movie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.movie.R;
import com.example.android.movie.databinding.MainButtonBinding;
import com.example.android.movie.ui.Movie.MainActivity;
import com.example.android.movie.ui.Tv.TvMain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainButtonChoose extends AppCompatActivity implements View.OnClickListener {
    MainButtonBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_button);

        mBinding.movieButton.setOnClickListener(this);
        mBinding.tvButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie_button:
                Intent movieIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(movieIntent);
                break;
            case R.id.tv_button:
                Intent tvIntent = new Intent(getBaseContext(), TvMain.class);
                startActivity(tvIntent);
                break;

        }
    }


}


