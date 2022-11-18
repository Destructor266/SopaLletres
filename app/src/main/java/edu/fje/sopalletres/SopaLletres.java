package edu.fje.sopalletres;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
    final int[] Puntuacio = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopalletres);

        final String[] Lletres = getResources().getStringArray(R.array.paraules);


        GenerarSopaLleteres(Lletres, btn);
        SopaDeLletresVertical(Lletres, btn);
        GenerarParaulesATrobar(Lletres);
        FuncionalitatBoto(Lletres, Puntuacio, btn);
    }

    private void GenerarSopaLleteres(String[] Lletres, Button[][] btnr){
        TableLayout TlSopaLletres = findViewById(R.id.tlSopa);
        TableRow trSopaLletres = null;

        for (int columna = 0; columna < Lletres.length; columna++){

            trSopaLletres = new TableRow(this);
            TlSopaLletres.addView(trSopaLletres);

            for (int fila = 0; fila < Lletres[columna].length(); fila++) {

                btnr[columna][fila] = new Button(this);
                btnr[columna][fila].setText(String.valueOf(Lletres[columna].charAt(fila)));
                trSopaLletres.addView(btnr[columna][fila]);
            }
        }
    }

    private void SopaDeLletresVertical(String[] Lletres, Button[][] btnc){
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

            trParaulesATrobar.addView(tv);
            tv.setTextSize(28);
        }
    }

    private void FuncionalitatBoto(String[] Lletres, int[] Puntuacio, Button[][] btn){
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
                                PuntuacioBaseDeDades(Puntuacio);

                            } else if (paraulaComprovar.equals(Lletres[1])){

                                Puntuacio[0] += 10;
                                TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
                                TableRow ParaulaperTachar = (TableRow) tlParaulesATrobar.getChildAt(1);
                                TextView TextperTachar = (TextView) ParaulaperTachar.getChildAt(0);
                                TextperTachar.setPaintFlags(TextperTachar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                PuntuacioBaseDeDades(Puntuacio);

                            } else if (paraulaComprovar.equals(Lletres[2])){

                                Puntuacio[0] += 10;
                                TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
                                TableRow ParaulaperTachar = (TableRow) tlParaulesATrobar.getChildAt(2);
                                TextView TextperTachar = (TextView) ParaulaperTachar.getChildAt(0);
                                TextperTachar.setPaintFlags(TextperTachar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                PuntuacioBaseDeDades(Puntuacio);

                            } else if (paraulaComprovar.equals(Lletres[3])){

                                Puntuacio[0] += 10;
                                TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
                                TableRow ParaulaperTachar = (TableRow) tlParaulesATrobar.getChildAt(3);
                                TextView TextperTachar = (TextView) ParaulaperTachar.getChildAt(0);
                                TextperTachar.setPaintFlags(TextperTachar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                PuntuacioBaseDeDades(Puntuacio);

                            }
                        }
                        if (Puntuacio[0] == 40){
                            DialegPartidaCompletada().show();
                        }
                        finalBtnr[finalColumna][finalFila].setBackgroundColor(Color.RED);
                    }
                });
            }
        }
    }

    private void PuntuacioBaseDeDades(int[] Puntuacio){
        SQLiteDatabase baseDades = null;
        int dPuntuacio = Puntuacio[0];

        String BASE_DADES = "SopaLletres";
        String TAULA = "Puntuacions";
        Calendar cal = new GregorianCalendar();
        String data = cal.get(Calendar.DAY_OF_MONTH) + "" + cal.get(Calendar.MONTH) + "" +
                cal.get(Calendar.YEAR);

        try {
            baseDades = this.openOrCreateDatabase(BASE_DADES, MODE_PRIVATE, null);
            baseDades.execSQL("CREATE TABLE IF NOT EXISTS " + TAULA + "(puntuacio INT(4), " +
                    "data VARCHAR(20));");
            baseDades.execSQL("INSERT INTO " + TAULA + " (puntuacio, data)" + " VALUES " + "(" +
                    dPuntuacio + "," + data + ");");

        }finally {
            if (baseDades != null){
                baseDades.close();
            }
        }
    }

    public Dialog DialegPartidaCompletada () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_start_game)
                .setNegativeButton(R.string.start, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}
