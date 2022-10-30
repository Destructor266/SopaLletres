package edu.fje.sopalletres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Level;

public class SopaLletres extends AppCompatActivity {

    private static final String[][] Lletres = {
            {"H", "O", "L", "A"},
            {"A", "D", "E", "U"},
            {"C", "A", "S", "A"},
            {"P", "O", "C", "A"},
            {"P", "E", "R", "E"},
            {},
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopalletres);

        GenerarSopaLleteres();
        GenerarParaulesATrobar();
    }

    private void GenerarSopaLleteres(){
        TableLayout TlSopaLletres = findViewById(R.id.tlSopa);
        TableRow trSopaLletres = null;

        for (int columna = 0; columna < Lletres.length; columna++){

            trSopaLletres = new TableRow(this);
            TlSopaLletres.addView(trSopaLletres);

            for (int fila = 0; fila < Lletres[columna].length; fila++){

                int paraula = Lletres[columna].length;

                int LletraAleat = (int) Math.abs((Math.random() * Lletres.length - paraula));
                int IDaleat = (int) Math.abs((Math.random() * 4));

                Button btn = new Button(this);
                btn.setText(Lletres[columna][fila]);

                if (LletraAleat == IDaleat){
                    //btn.setText(Lletres[LletraAleat][LletraAleat]);
                }
                trSopaLletres.addView(btn);
            }
        }
    }

    private void GenerarParaulesATrobar(){
        TableLayout tlParaulesATrobar = findViewById(R.id.tlParaulesATrobar);
        TableRow trParaulesATrobar = null;

        for (int columna = 0; columna < Lletres.length; columna++){

            trParaulesATrobar = new TableRow(this);
            tlParaulesATrobar.addView(trParaulesATrobar);

            for (int fila = 0; fila < Lletres[columna].length; fila++) {

                TextView tv = new TextView(this);
                tv.setText(Lletres[columna][fila]);

                trParaulesATrobar.addView(tv);
            }
        }
    }
}
