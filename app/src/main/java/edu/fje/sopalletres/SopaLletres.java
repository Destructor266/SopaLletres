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

import java.util.logging.Level;

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
        FuncionalitatBoto(Lletres, btn);
        GenerarParaulesATrobar(Lletres);
    }

    private void GenerarSopaLleteres(String[] Lletres, Button[][] btnr){
        TableLayout TlSopaLletres = findViewById(R.id.tlSopa);
        TableRow trSopaLletres = null;
        int ID = 0;

        for (int columna = 0; columna < Lletres.length; columna++){

            trSopaLletres = new TableRow(this);
            TlSopaLletres.addView(trSopaLletres);

            for (int fila = 0; fila < Lletres[columna].length(); fila++) {

                btnr[columna][fila] = new Button(this);
                btnr[columna][fila].setId(ID);
                ID++;
                btnr[columna][fila].setText(String.valueOf(Lletres[columna].charAt(fila)));
                trSopaLletres.addView(btnr[columna][fila]);

            }
        }
        //return btnr;
    }

    private void SopaDeLletresVertical(String[] Lletres, Button[][] btnc){
        //buttons[0][1].setText(String.valueOf(Lletres[0].charAt(0)));
        //buttons[1][1].setText(String.valueOf(Lletres[0].charAt(1)));
        //buttons[2][1].setText(String.valueOf(Lletres[0].charAt(2)));
        //buttons[3][1].setText(String.valueOf(Lletres[0].charAt(3)));

        for (int i = 0; i < Lletres.length; i++) {
            btnc[i][1].setText(String.valueOf(Lletres[0].charAt(i)));
            btnc[i][3].setText(String.valueOf(Lletres[2].charAt(i)));
        }
    }


    private void FuncionalitatBoto(String[] Lletres, Button[][] btn){
        Button[][] finalBtnr = btn;
        String paraula = "";
        StringBuilder paraulaB = new StringBuilder(paraula);

        for (int columna = 0; columna < Lletres.length; columna++) {
            for (int fila = 0; fila < Lletres[columna].length(); fila++) {
                int finalColumna = columna;
                int finalFila = fila;
                btn[columna][fila].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String TextBoto = finalBtnr[finalColumna][finalFila].getText().toString();
                        paraulaB.append(TextBoto);
                        System.out.println(TextBoto);
                        if (paraulaB.length() == Lletres[finalColumna].length()){
                            System.out.println("Primer IF");
                            String paraulaComprovar = paraulaB.toString();
                            System.out.println("Comprovacio " + paraulaComprovar);
                            System.out.println(Lletres[0]);
                            if (paraulaComprovar.equals(Lletres[0])){
                                System.out.println("Segon IF");
                                finalBtnr[finalColumna][finalFila].setEnabled(false);
                                finalBtnr[finalColumna][finalFila].setBackgroundColor(Color.GREEN);
                            }
                        }
                        click++;
                        System.out.println(paraulaB.length());
                        System.out.println(Lletres[finalColumna].charAt(finalFila));
                        finalBtnr[finalColumna][finalFila].setBackgroundColor(Color.RED);
                    }
                });
            }
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
            //tv.getPaint().setStrikeThruText(true);

            trParaulesATrobar.addView(tv);
        }
    }
}
