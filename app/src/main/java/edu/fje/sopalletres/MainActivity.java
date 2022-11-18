package edu.fje.sopalletres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Atributs de puntuacio i data recuperada de la base de dades
    private int puntuacioRecuperada = 0;
    private String dataRecuperada = "";

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

        //Crides a les funcions de la clase no sobreescrites

        RecuperadaDades();
        LeaderBoard();
    }

    // Canvia la activitat al premer el boto "Jugar"

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SopaLletres.class);
        startActivity(intent);
    }

    //Mostra el menu d'opcions i al premer l'opcio "ajuda" crida al component webview

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

    /**
     * Mostra el component webview amb una explicacio de l'aplicacio.
     */

    private void obrirAjuda() {
        setContentView(R.layout.webview);
        WebView navegador = (WebView) findViewById(R.id.webkit);

        navegador.setWebViewClient(new WebViewClient());
        navegador.loadData("<html>" +
                "                   <body style=\"background-color:orange;\">" +
                "                       <h1 style=\"text-align: center;\">Benvinguts a la ajuda de la Sopa de lletres</h1>" +
                "                       <p>En aquesta secció s'explicara com funciona aquesta sopa de lletres<p>" +
                "                       <p>El primer de tot per poder començar a jugar has d'anar a la pantalla de benvinguda de l'aplicació y premer sobre el boto de jugar<p>" +
                "                       <p>Una vegada ho hagis fet accediras al joc</p>" +
                "                       <h2 style=\"text-align: center;\">Funcionament del joc:</h2>" +
                "                       <ul>" +
                "                           <li>Tauler:</li>" +
                "                               <p>El joc principal, en aqui has de trobar les lletres que formen les paraules que estan repartides entre els diferents butons," +
                "                               Es poden seleccionar fins a quatre butons per paraula, sabrem que estan seleccionats perque aquest hauran cambiat de color," +
                "                               si aquest quatre butons formen una de les paraules, sumaran 10 punts al usuari</p>" +
                "                           <li>Taula de paraules a trobar:</li>" +
                "                               <p>En aquest apartat estan les paraules que s'han de trobar a la sopa de lletres</p>" +
                "                       </ul>" +
                "                       <a href=\"https://es.wikipedia.org/wiki/Sopa_de_letras\">Mes informacio sobre la sopes de lletres</a>" +
                "                   </body>" +
                "                </html>", "text/html", "UTF-8");

    }

    /**
     * Recupera les puntuacions i les dates de la base de dades.
     */

    private void RecuperadaDades(){
        SQLiteDatabase baseDades = null;
        String BASE_DADES = "SopaLletres";
        String TAULA = "Puntuacions";

        try {
            baseDades = this.openOrCreateDatabase(BASE_DADES, MODE_PRIVATE, null);
            Cursor c = baseDades.rawQuery("SELECT puntuacio, data" + " FROM " + TAULA + " ;", null);

            int columnaPuntuacio = c.getColumnIndex("puntuacio");
            int columnaData = c.getColumnIndex("data");

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

    /**
     * Mostra la puntuacio i les dates recuperades de una base de dades en la pantalla principal.
     */

    private void LeaderBoard(){
        LinearLayout LlTaulaPuntuacions = findViewById(R.id.TaulaPuntuacions);
        TextView tv = new TextView(this);
        tv.setText("Data " + this.dataRecuperada + " | " + "Puntuacio " + this.puntuacioRecuperada);
        LlTaulaPuntuacions.addView(tv);
        tv.setTextSize(20);
    }
}