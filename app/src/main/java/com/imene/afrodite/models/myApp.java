package com.imene.afrodite.models;

import android.app.Application;


public class myApp extends Application {

    private Client user;

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }
}
