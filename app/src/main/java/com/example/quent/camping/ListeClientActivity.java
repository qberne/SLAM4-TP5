package com.example.quent.camping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ListeClientActivity extends AppCompatActivity {

    private Camping unCamping;
    private Client unClient;
    private ArrayList<Client> listClient;
    private ListView listViewClient;
    private int positionDansLaListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_client);

        unCamping = (Camping) getIntent().getSerializableExtra("leCamping");
        listClient = unCamping.getListClient();

        listViewClient = (ListView) findViewById(R.id.ListViewClient);
        ClientAdapter adapter = new ClientAdapter(this, listClient);
        listViewClient.setAdapter(adapter);

        listViewClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionDansLaListe = position;
                Client c = listClient.get(positionDansLaListe);

                Intent i = new Intent(getApplicationContext(), ClientActivity.class);
                i.putExtra("leClientSelectionne", (Serializable) c);
                startActivityForResult(i, 11);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(getApplicationContext(), "ici LISTE : retour de l'activité Client", Toast.LENGTH_LONG).show();

        switch (requestCode) {
            case 11:
                switch (resultCode) {
                    case 111:
                        //on revient de l'activity client car on a demandé à changer les coordonnées du client
                        unClient = (Client) data.getSerializableExtra("clientRenvoye");
                        Log.i("INFO", "client recup : " + unClient.toString());
                        //Toast.makeText(getApplicationContext(), "position : "+positionDansLaListe, Toast.LENGTH_LONG).show();
                        listClient.set(positionDansLaListe, unClient);
                        ClientAdapter ca = new ClientAdapter(this, listClient);
                        listViewClient.setAdapter(ca);
                }


        }
    }
}
