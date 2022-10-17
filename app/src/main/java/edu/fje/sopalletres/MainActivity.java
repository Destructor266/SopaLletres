package edu.fje.sopalletres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*
    Diagrama UML
    Barra acció
    Aleatorietat aparació mots
    Extreure mots en XML
    Ajuda amb WebView
    SQLite
    Extreure els mots de contactes
    MVC
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button boto;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().setStatusBarColor(10);

        boto = findViewById(R.id.btJugar);
        boto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SopaLletres.class);
        startActivity(intent);
    }
}