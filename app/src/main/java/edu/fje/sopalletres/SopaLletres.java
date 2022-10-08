package edu.fje.sopalletres;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SopaLletres extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopalletres);
        getWindow().setStatusBarColor(10);

        Intent intent = getIntent();
    }
}
