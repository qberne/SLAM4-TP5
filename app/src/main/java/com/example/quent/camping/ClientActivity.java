package com.example.quent.camping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.quent.camping.R.id.datePickerDateNaiss;

public class ClientActivity extends AppCompatActivity {

    Intent i2;

    private Camping unCamping;
    private Client unClient;

    private Button btnEnregistrer;
    private Button btnEffacer;
    private Button btnModifier;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_client);

        btnEnregistrer = (Button) findViewById(R.id.buttonEnregistrer);
        btnEffacer = (Button)findViewById(R.id.buttonEffacer);
        btnModifier = (Button)findViewById(R.id.buttonModifier);
        DatePicker datePickerDateNaiss = (DatePicker)findViewById(R.id.datePickerDateNaiss);

        Date date = new Date(); // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        btnEnregistrer.setOnClickListener(btnclick);
        btnEffacer.setOnClickListener(btnclick);
        btnModifier.setOnClickListener(btnclick);
        datePickerDateNaiss.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), calendarclick);

        unCamping = (Camping) getIntent().getSerializableExtra("campingCree");

    }

    protected void onResume() {
        Log.i("INFO", "RESUME de l'activite Client");
        super.onResume();
        //unCamping = (Camping) getIntent().getSerializableExtra("leCamping");

        Client clientRecup = (Client) getIntent().getSerializableExtra("leClientSelectionne");

        if (clientRecup == null) {
            btnModifier.setEnabled(false);
            unClient = new Client();
        }
        else {/*quand l'activité revient au premier plan c'est cette méthode qui s'exécute*/
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            btnEffacer.setEnabled(false);
            btnEnregistrer.setEnabled(false);
            unClient = clientRecup;
            ((EditText) findViewById(R.id.editTextNomClient)).setText(unClient.getNom());
            ((EditText) findViewById(R.id.editTextPrenomClient)).setText(unClient.getPrenom());
            ((EditText) findViewById(R.id.editTextDateNaissClient)).setText(sdf.format(unClient.getDateNais()));
            ((EditText) findViewById(R.id.editTextNumPortableClient)).setText(unClient.getNumPortable());
        }
    }

    private void clearEditText(){
        ((EditText)findViewById(R.id.editTextNomClient)).setText("");
        ((EditText)findViewById(R.id.editTextPrenomClient)).setText("");
        ((EditText)findViewById(R.id.editTextDateNaissClient)).setText("");
        ((EditText)findViewById(R.id.editTextNumPortableClient)).setText("");

        ((EditText)findViewById(R.id.editTextNomClient)).requestFocus();
    }

    private View.OnClickListener btnclick = new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            switch (v.getId()) {
                case R.id.buttonEnregistrer:
                    Toast.makeText(getApplicationContext(), "Enregistrement du client en cours", Toast.LENGTH_SHORT).show();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


                    unClient.setNom(((EditText)findViewById(R.id.editTextNomClient)).getText().toString());
                    unClient.setPrenom(((EditText)findViewById(R.id.editTextPrenomClient)).getText().toString());

                    try {
                        unClient.setdateNais(sdf.parse(((EditText)findViewById(R.id.editTextDateNaissClient)).getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    unClient.setNumPortable(((EditText)findViewById(R.id.editTextNumPortableClient)).getText().toString());

                    //Toast.makeText(getApplicationContext(), c.toString(), Toast.LENGTH_SHORT).show();

                    unCamping.addClient(unClient);

                    Toast.makeText(getApplicationContext(), unCamping.toString(), Toast.LENGTH_SHORT).show();

                    clearEditText();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("campingRenvoye", (Serializable)unCamping);
                    setResult(100, i);

                    break;
                case R.id.buttonEffacer:
                    clearEditText();
                    break;
                case R.id.buttonModifier:
                    unClient.setNom(((EditText) findViewById(R.id.editTextNomClient)).getText().toString());
                    unClient.setPrenom(((EditText) findViewById(R.id.editTextPrenomClient)).getText().toString());
                    unClient.setNumPortable(((EditText) findViewById(R.id.editTextNumPortableClient)).getText().toString());

                    Log.i("INFO", "client modifie : "+ unClient.toString());

                    i2 = new Intent(getApplicationContext(), ListeClientActivity.class);
                    i2.putExtra("clientRenvoye", unClient);
                    setResult(111, i2);
                    break;
            }
        }
    };



    private DatePicker.OnDateChangedListener calendarclick = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            switch (view.getId()){
                case datePickerDateNaiss:
                    ((EditText)findViewById(R.id.editTextDateNaissClient)).setText(String.valueOf(dayOfMonth)+'/'+String.valueOf(monthOfYear)+'/'+String.valueOf(year));
                    break;
            }
        }
    };



}
