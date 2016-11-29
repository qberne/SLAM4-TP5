package com.example.quent.camping;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by quent on 28/11/2016.
 */

public class Camping implements Serializable {
    private ArrayList<Client> listClient;

    public Camping() {
        listClient = new ArrayList<Client>();
    }

    public void addClient(Client c){
        listClient.add(c);
    }

    @Override
    public String toString() {
        StringBuilder caract = new StringBuilder("\n");

        for (Client c:listClient){
            caract.append(c.toString());
            caract.append("\n--\n");
        }

        return caract.toString();
    }
}
