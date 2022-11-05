package edu.fje.sopalletres;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class SopaLletres extends AppCompatActivity {

    /*
    private static final String[][] Lletres = {
            {"E", "Y", "", ""},
            {"P", "O", "C", ""},
            {"C", "A", "S", "A"},
            {"", "", "", ""},
    };
    */

    private Button[][] btn = new Button[6][4];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopalletres);

        final String[] Lletres = getResources().getStringArray(R.array.paraules);
        final int[] Puntuacio = {0};
        final String BASE_DADES = "SopaLletres";
        final String TAULA = "Puntuacions";

        GenerarSopaLleteres(Lletres, btn);
        SopaDeLletresVertical(Lletres, btn);
        GenerarParaulesATrobar(Lletres);
        FuncionalitatBoto(Lletres, Puntuacio, btn);
        PuntuacioBaseDeDades(Puntuacio, BASE_DADES, TAULA);

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

    private int[] FuncionalitatBoto(String[] Lletres, int[] Puntuacio, Button[][] btn){
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
                        if (paraulaB.length() == Lletres[finalColumna].length()){
                            String paraulaComprovar = paraulaB.toString();
                            paraulaB.setLength(0);
                            if (paraulaComprovar.equals(Lletres[0])){
                                Puntuacio[0] += 10;
                                TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
                                TableRow ParaulaperTachar = (TableRow) tlParaulesATrobar.getChildAt(0);
                                TextView TextperTachar = (TextView) ParaulaperTachar.getChildAt(0);
                                TextperTachar.setPaintFlags(TextperTachar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            } else if (paraulaComprovar.equals(Lletres[1])){
                                Puntuacio[0] += 10;
                                TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
                                TableRow ParaulaperTachar = (TableRow) tlParaulesATrobar.getChildAt(1);
                                TextView TextperTachar = (TextView) ParaulaperTachar.getChildAt(0);
                                TextperTachar.setPaintFlags(TextperTachar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            } else if (paraulaComprovar.equals(Lletres[2])){
                                Puntuacio[0] += 10;
                                TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
                                TableRow ParaulaperTachar = (TableRow) tlParaulesATrobar.getChildAt(2);
                                TextView TextperTachar = (TextView) ParaulaperTachar.getChildAt(0);
                                TextperTachar.setPaintFlags(TextperTachar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            }
                        }
                        System.out.println(Puntuacio[0]);
                        finalBtnr[finalColumna][finalFila].setBackgroundColor(Color.RED);
                    }
                });
            }
        }
        return Puntuacio;
    }

    private void PuntuacioBaseDeDades(int[] Puntuacio, String BASE_DADES, String TAULA){
        SQLiteDatabase baseDades = null;
        int dPuntuacio = Puntuacio[0];

        try {
            baseDades = this.openOrCreateDatabase(BASE_DADES, MODE_PRIVATE, null);

            baseDades.execSQL("CREATE TABLE IF NOT EXISTS " + TAULA + "(puntuacio INT(4), " +
                    "data VARCHAR);");

            baseDades.execSQL("INSERT INTO " + TAULA + " (puntuacio, data)" + " VALUES (\" + dPuntuacio + \", '\" + date() + \"')");

            Cursor c = baseDades.rawQuery("SELECT puntuacio, data" + " FROM " + TAULA, null);

            int columnaPuntuacio = c.getColumnIndex("puntuacio");
            int columnaData = c.getColumnIndex("data");


            if (c != null){
                if (c.isBeforeFirst()){
                    c.moveToFirst();
                    int i = 0;

                    do {
                        i++;

                        int puntuacio = c.getInt(columnaPuntuacio);
                        int data = c.getInt(columnaData);

                    }while (c.moveToNext());

                }
            }
        }finally {
            if (baseDades != null){
                baseDades.close();
            }
        }


    }




}
