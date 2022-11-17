package edu.fje.sopalletres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*
    Diagrama UML
    Barra acció
    menu
    Aleatorietat aparació mots
    Extreure mots en XML
    Ajuda amb WebView
    SQLite Puntuació
    Extreure els mots de contactes
    MVC
     */

    int puntuacioRecuperada = 0;
    String dataRecuperada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btJugar;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btJugar = findViewById(R.id.btJugar);
        btJugar.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecuperadaDades();
        LeaderBoard();
    }

    private void RecuperadaDades(){
        SQLiteDatabase baseDades = null;
        String BASE_DADES = "SopaLletres";
        String TAULA = "Puntuacions";

        try {
            baseDades = this.openOrCreateDatabase(BASE_DADES, MODE_PRIVATE, null);
            Cursor c = baseDades.rawQuery("SELECT puntuacio, data" + " FROM " + TAULA + " ;", null);

            int columnaPuntuacio = c.getColumnIndex("puntuacio");
            int columnaData = c.getColumnIndex("data");

            //Log.i("cursor", String.valueOf(c.getCount()));

            if (c != null){
                c.moveToLast();
                this.puntuacioRecuperada = c.getInt(columnaPuntuacio);
                this.dataRecuperada = c.getString(columnaData);
            }

        }finally {
            if (baseDades != null){
                baseDades.close();
            }
        }
    }

    private void LeaderBoard(){
        LinearLayout LlTaulaPuntuacions = findViewById(R.id.TaulaPuntuacions);
        TextView tv = new TextView(this);
        tv.setText(this.dataRecuperada + " | " + this.puntuacioRecuperada);
        LlTaulaPuntuacions.addView(tv);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SopaLletres.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ajuda:
                obrirAjuda();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void obrirAjuda() {
        setContentView(R.layout.webview);
        WebView navegador = (WebView) findViewById(R.id.webkit);

        navegador.setWebViewClient(new WebViewClient());
        navegador.loadData("<html>" +
                "                   <body>" +
                "                       <h1>Benvinguts a la ajuda de la Sopa de lletres</h1>" +
                "                       <p>En aquesta secció s'explicara com funciona aquesta sopa de lletres<p>" +
                "                       <p>El primer de tot per poder començar a jugar has d'anar a la pantalla de benvinguda de l'aplicació y premer sobre el boto de jugar<p>" +
                "                       <p>Una vegada ho hagis fet accediras al joc</p>" +
                "                       <h2>Funcionament del joc:</h2>" +
                "                       <ul>" +
                "                           <li>Timer:</li>" +
                "                               <p>La sopa de lletres te un temps limit en la part superior de la pantalla, quan aquest temps acaba finalitza el joc</p>" +
                "                           <li>Tauler:</li>" +
                "                               <p>El joc principal, en aqui has de trobar les lletres que formen les paraules que estan repartides entre els diferents butons," +
                "                               Es poden seleccionar fins a quatre butons a la vegada, sabrem que estan seleccionats perque aquest hauran cambiat de color," +
                "                               si aquest quatre butons formen una de les paraules, cambiaran de color a una altre color y sumaran 10 punts al usuari, si no formen cap paraula retornar al estil original</p>" +
                "                           <li>Taula de paraules a trobar:</li>" +
                "                               <p>En aquest apartat estan les paraules que s'han de trobar a la sopa de lletres</p>" +
                "                       </ul>" +
                "                       <a href=\"https://es.wikipedia.org/wiki/Sopa_de_letras\">Mes informacio sobre la sopes de lletres</a" +
                "                   </body>" +
                "                </html>", "text/html", "UTF-8");

    }


}