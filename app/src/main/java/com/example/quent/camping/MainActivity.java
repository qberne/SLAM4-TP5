package com.example.quent.camping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Intent i;
    private Camping unCamping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unCamping = new Camping();

        Button buttonAjouterClient = (Button) findViewById(R.id.buttonAjouterClient);
        buttonAjouterClient.setOnClickListener(btnclick);

        Button buttonListeClient = (Button) findViewById(R.id.buttonListeClient);
        buttonListeClient.setOnClickListener(btnclick);
    }

    private Button.OnClickListener btnclick = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonAjouterClient:
                    i = new Intent(getApplicationContext(),ClientActivity.class);
                    i.putExtra("campingCree", (Serializable)unCamping);
                    startActivityForResult(i,1);
                    break;
                case R.id.buttonListeClient:
                    Log.i("dans MainActivity : ", "Appel de l'activité LISTEclient");
                    Intent i2 = new Intent(getApplicationContext(), ListeClientActivity.class);
                    i2.putExtra("leCamping", (Serializable) unCamping);
                    startActivityForResult(i2, 2);
                    break;
            }
        }
    };

    protected void onActivityResult(int RequestCode , int ResultCode , Intent data) {
        switch (RequestCode) {
            case 1:
                switch (ResultCode) {
                    case 100:
                        Toast.makeText(getApplicationContext(), "retour de l'activité Client", Toast.LENGTH_LONG).show();

                        unCamping = (Camping) data.getSerializableExtra("campingRenvoye");

                        break;
                }
        }
    }

}
