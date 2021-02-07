package com.imene.afrodite.models;

import java.util.ArrayList;

public class Cadeau {
    private String nom;
    private float PrixCad;
    private String description;
    private int NombreDePointsCad;
    private int numCadeau ;
    private int Niveau;
    private int Quantite;
    private int drawable ;

    public Cadeau() {
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public Cadeau(String nom, float PrixCad, String description, int NombreDePointsCad, int numCadeau, int niveau, int quantite, int drawable ) {
        this.nom = nom;
        this.PrixCad = PrixCad;
        this.description = description;
        this.NombreDePointsCad = NombreDePointsCad;
        this.numCadeau = numCadeau;
        this.Niveau = niveau;
        this.Quantite = quantite;
        this.drawable = drawable;
    }

    public int getNumCadeau() {
        return numCadeau;
    }

    public void setNumCadeau(int numCadeau) {
        this.numCadeau = numCadeau;
    }

    public int getNiveau() {
        return Niveau;
    }

    public void setNiveau(int niveau) {
        this.Niveau = niveau;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int quantite) {
        this.Quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrixCad() {
        return PrixCad;
    }

    public void setPrixCad(float prixCad) {
        this.PrixCad = prixCad;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNombreDePointsCad() {
        return NombreDePointsCad;
    }

    public void setNombreDePointsCad(int nombreDePointsCad) {
        this.NombreDePointsCad = nombreDePointsCad;
    }

    public Cadeau(String pNom, float PrixCad, String nom, int NombreDePointsCad, int numCadeau, int quantite) {
        this.nom = nom;
    }

    public Cadeau(String nom) {
        this.nom = nom;
    }

    public static ArrayList<Cadeau> createContactsList(int numContacts) {
        ArrayList<Cadeau> contacts = new ArrayList<Cadeau>();

        for (int i = 1; i <= numContacts; i++) {
           contacts.add(new Cadeau("cadeau"));
        }

        return contacts;
    }
}
