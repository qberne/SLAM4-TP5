package com.example.quent.camping;/*
 * Client.java
 * Created on 19 sept 2016
 * by F. de Robien 
 */


import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    private long id;
    private String nom;
    private String prenom;
    private Date dateNais;
    private String numPortable;

    /**
     * Creates a new instance of Client
     **/

    public Client(String n, String p, Date dn, String numP) {
       
        nom = n;
        prenom = p;
        dateNais = dn;
        numPortable = numP;
    }



    public long getId() { /*retourne l'id du client ajouté automatiquement*/
        return id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String unNom) {
        nom = unNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String unPrenom) {
        prenom = unPrenom;
    }

    public Date getDateNais() {
        return dateNais;
    }

    public void setdateNais(Date uneDateNais) {
        dateNais = uneDateNais;
    }

    public String getNumPortable() {
        return numPortable;
    }

    public void setNumPortable(String numPortable) {
        this.numPortable = numPortable;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder caract = new StringBuilder("\n");
        caract.append("nom :").append(nom.toUpperCase()).append(" - ");
        caract.append("prenom :").append(prenom).append(" - ");
        String d = sdf.format(dateNais);
        caract.append("date de Naissance :").append(d).append(" - ");
        caract.append("numéro de portable :").append(numPortable).append(" \n");
        return caract.toString();
    }

}
