package com.auchan.scanners.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Scanner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int scannerNumber;
    private String loggedUser;
    private boolean inService;
    private LocalDateTime timeOfLogin;

    public Scanner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScannerNumber() {
        return scannerNumber;
    }

    public void setScannerNumber(int scannerNumber) {
        this.scannerNumber = scannerNumber;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isInService() {
        return inService;
    }

    public void setInService(boolean inService) {
        this.inService = inService;
    }

    public LocalDateTime getTimeOfLogin() {
        return timeOfLogin;
    }

    public void setTimeOfLogin(LocalDateTime timeOfLogin) {
        this.timeOfLogin = timeOfLogin;
    }
}
