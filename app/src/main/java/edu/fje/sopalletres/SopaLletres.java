package edu.fje.sopalletres;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SopaLletres extends AppCompatActivity {

    /*
    private static final String[][] Lletres = {
            {"E", "Y", "", ""},
            {"P", "O", "C", ""},
            {"C", "A", "S", "A"},
            {"", "", "", ""},
    };
     */

    private int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopalletres);

        final String[] Lletres = getResources().getStringArray(R.array.paraules);

        GenerarSopaLleteres(Lletres);
        GenerarParaulesATrobar(Lletres);
    }

    private void GenerarSopaLleteres(String[] Lletres){
        TableLayout TlSopaLletres = findViewById(R.id.tlSopa);
        TableRow trSopaLletres = null;
        Button btnr = null;


        for (int columna = 0; columna < Lletres.length; columna++){

            trSopaLletres = new TableRow(this);
            TlSopaLletres.addView(trSopaLletres);

            for (int fila = 0; fila < Lletres[columna].length(); fila++) {

                int paraula = Lletres[columna].length();

                int LletraAleat = (int) Math.abs((Math.random() * Lletres.length - paraula));

                btnr = new Button(this);
                btnr.setText(String.valueOf(Lletres[columna].charAt(fila)));

                trSopaLletres.addView(btnr);

                Button finalBtnr = btnr;
                btnr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(click);
                        click++;
                        finalBtnr.setBackgroundColor(Color.RED);
                        if (click > 4){
                            //finalBtnr.setBackground();
                        }
                    }
                });
            }
            //btnc.setText(String.valueOf(Lletres[columna].charAt(1)));
            //trSopaLletres.addView(btnc);

            //trSopaLletres.addView(btnr);
        }
    }

    private void GenerarParaulesATrobar(String[] Lletres){
        TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
        TableRow trParaulesATrobar = null;

        for (int columna = 0; columna < Lletres.length; columna++){

            trParaulesATrobar = new TableRow(this);
            tlParaulesATrobar.addView(trParaulesATrobar);

            TextView tv = new TextView(this);
            tv.setText(String.valueOf(Lletres[columna]));

            trParaulesATrobar.addView(tv);
        }
    }
}
