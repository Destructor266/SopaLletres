package edu.fje.sopalletres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SopaLletres extends AppCompatActivity {

    private String[][] Lletres = {
            {"H", "O", "L", "A"},
            {"A","D","E","U"},
            {"C", "A", "S", "A"},
            {"P","O","C","A"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopalletres);
        //getWindow().setStatusBarColor(10);

        //Intent intent = getIntent();

        GenerarSopaLleteres();
        GenerarParaulesATrobar();
    }

    private void GenerarSopaLleteres(){
        TableLayout TlSopaLletres = findViewById(R.id.tlSopa);
        TableRow trSopaLletres = null;

        int columna = 0;

        while(columna < Lletres.length){

            int fila = 0;

            trSopaLletres = new TableRow(this);
            TlSopaLletres.addView(trSopaLletres);

            //System.out.println("Columna: " + columna);

            while (fila < Lletres[columna].length){

                //System.out.println("fila: " + fila);

                Button btn = new Button(this);
                btn.setText(Lletres[columna][fila]);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(v.getId());
                    }
                });

                trSopaLletres.addView(btn);

                fila++;
            }
            columna++;
        }
    }

    private void GenerarParaulesATrobar(){
        TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
        TableRow trParaulesATrobar = null;

        int columna = 0;

        while(columna < Lletres.length){

            int fila = 0;

            trParaulesATrobar = new TableRow(this);
            tlParaulesATrobar.addView(trParaulesATrobar);

            //System.out.println("Columna: " + columna);

            while (fila < Lletres[columna].length){

                //System.out.println("fila: " + fila);

                TextView tv = new TextView(this);
                tv.setText(Lletres[columna][fila]);

                trParaulesATrobar.addView(tv);

                fila++;
            }
            columna++;
        }
    }
}
