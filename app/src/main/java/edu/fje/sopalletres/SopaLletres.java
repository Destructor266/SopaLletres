package edu.fje.sopalletres;

import android.annotation.SuppressLint;
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
    private Button[][] btn = new Button[6][4];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopalletres);

        final String[] Lletres = getResources().getStringArray(R.array.paraules);

        GenerarSopaLleteres(Lletres, btn);
        SopaDeLletresVertical(Lletres, btn);
        GenerarParaulesATrobar(Lletres);
    }

    private Button[][] GenerarSopaLleteres(String[] Lletres, Button[][] btnr){
        TableLayout TlSopaLletres = findViewById(R.id.tlSopa);
        TableRow trSopaLletres = null;
        //Button[] btnr = new Button[16];
        int ID = 0;

        for (int columna = 0; columna < Lletres.length; columna++){

            trSopaLletres = new TableRow(this);
            TlSopaLletres.addView(trSopaLletres);

            for (int fila = 0; fila < Lletres[columna].length(); fila++) {

                int paraula = Lletres[columna].length();
                System.out.println(paraula);

                int LletraAleat = (int) Math.abs((Math.random() * Lletres.length - paraula));
                System.out.println(LletraAleat);

                btnr[columna][fila] = new Button(this);
                btnr[columna][fila].setId(ID);
                ID++;
                btnr[columna][fila].setText(String.valueOf(Lletres[columna].charAt(fila)));
                trSopaLletres.addView(btnr[columna][fila]);



                /*
                Button finalBtnr = btnr;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("click " + click);
                        click++;
                        finalBtnr.setBackgroundColor(Color.RED);
                        System.out.println("ID" + finalBtnr.getId());
                        if (click > 4){
                            //finalBtnr.setBackground();
                        }
                    }
                });
                 */


            }
        }
        return btnr;
    }


    //Generar array buttons de la funcion anterior y modificar los valores de las verticales

    private void SopaDeLletresVertical(String[] Lletres, Button[][] buttons){
        buttons[3][1].setText(String.valueOf(Lletres[0].charAt(2)));
    }





    private void GenerarParaulesATrobar(String[] Lletres){
        TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
        TableRow trParaulesATrobar = null;

        for (int columna = 0; columna < Lletres.length; columna++){

            trParaulesATrobar = new TableRow(this);
            tlParaulesATrobar.addView(trParaulesATrobar);

            TextView tv = new TextView(this);
            tv.setText(String.valueOf(Lletres[columna]));
            //tv.getPaint().setStrikeThruText(true);

            trParaulesATrobar.addView(tv);
        }
    }
}
