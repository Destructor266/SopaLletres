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
            {"A","D","E","U"},
            {"C", "A", "S", "A"},
            {"P","O","C","A"},
            {"P","E","R","E"},
            {},
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

        int ID = 0;

        for (int columna = 0; columna < Lletres.length; columna++){

            trSopaLletres = new TableRow(this);
            TlSopaLletres.addView(trSopaLletres);

            //System.out.println("Columna: " + columna);

            for (int fila = 0; fila < Lletres[columna].length; fila++){



                //System.out.println("fila: " + fila);

                // IMPORTANTE
                int paraula = Lletres[columna].length;

                //System.out.println("Paraula: " + paraula);
                //System.out.println("Lletres.length" + Lletres.length);

                int LletraAleat = (int) Math.abs((Math.random() * Lletres.length - paraula));

                //System.out.println(LletraAleat);

                Button btn = new Button(this);
                btn.setId(ID);
                ID++;
                btn.setText(Lletres[columna][fila]);
                String buttonText = btn.getText().toString();

                if (ID == (int) Math.abs((Math.random() * ID))){
                    btn.setText(Lletres[columna][LletraAleat]);
                }


                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(v.getId());
                    }
                });

                
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

            //System.out.println("Columna: " + columna);

            for (int fila = 0; fila < Lletres[columna].length; fila++) {

                //System.out.println("fila: " + fila);

                TextView tv = new TextView(this);
                tv.setText(Lletres[columna][fila]);

                trParaulesATrobar.addView(tv);
            }
        }
    }
}
