package com.imene.afrodite.models;

import java.util.Date;

public class Client {
    private int codeClient;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
    private int nbPoint;
    private Date dateExpiration;
    private int memberShip_id;

    public Client(int codeClient, String prenom, int nbPoint, int memberShip_id) {
        this.codeClient = codeClient;
        this.prenom = prenom;
        this.nbPoint = nbPoint;
        this.memberShip_id = memberShip_id;
    }

    public Client() {
    }

    public int getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNbPoint() {
        return nbPoint;
    }

    public void setNbPoint(int nbPoint) {
        this.nbPoint = nbPoint;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getMemberShip_id() {
        return memberShip_id;
    }

    public void setMemberShip_id(int memberShip_id) {
        this.memberShip_id = memberShip_id;
    }
}
