package com.example.quent.camping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ActivityClient extends AppCompatActivity {

    private Camping unCamping;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_client);

        Button btnEnregistrer = (Button) findViewById(R.id.buttonEnregistrer);
        Button btnEffacer = (Button)findViewById(R.id.buttonEffacer);
        DatePicker datePickerDateNaiss = (DatePicker)findViewById(R.id.datePickerDateNaiss);

        Date date = new Date(); // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        btnEnregistrer.setOnClickListener(btnclick);
        btnEffacer.setOnClickListener(btnclick);
        datePickerDateNaiss.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), calendarclick);

        unCamping = (Camping) getIntent().getSerializableExtra("campingCree");

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

                    Client c = new Client();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


                    c.setNom(((EditText)findViewById(R.id.editTextNomClient)).getText().toString());
                    c.setPrenom(((EditText)findViewById(R.id.editTextPrenomClient)).getText().toString());

                    try {
                        c.setdateNais(sdf.parse(((EditText)findViewById(R.id.editTextDateNaissClient)).getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    c.setNumPortable(((EditText)findViewById(R.id.editTextNumPortableClient)).getText().toString());

                    //Toast.makeText(getApplicationContext(), c.toString(), Toast.LENGTH_SHORT).show();

                    unCamping.addClient(c);

                    Toast.makeText(getApplicationContext(), unCamping.toString(), Toast.LENGTH_SHORT).show();

                    clearEditText();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("campingRenvoye", (Serializable)unCamping);
                    setResult(100, i);

                    finish();

                    break;
                case R.id.buttonEffacer:
                    clearEditText();
                    break;
            }
        }
    };



    private DatePicker.OnDateChangedListener calendarclick = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            switch (view.getId()){
                case R.id.datePickerDateNaiss:
                    ((EditText)findViewById(R.id.editTextDateNaissClient)).setText(String.valueOf(dayOfMonth)+'/'+String.valueOf(monthOfYear)+'/'+String.valueOf(year));
                    break;
            }
        }
    };
}
