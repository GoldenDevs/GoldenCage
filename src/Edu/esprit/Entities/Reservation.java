/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edu.esprit.Entities;

import java.util.Date;

/**
 *
 * @author Touch media
 */
public class Reservation {

    private int id;
    private Date date_debut;
    private Date date_fin;
    private String id_client;
    private int id_offre;

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Reservation(String offre, Date date_debut, Date date_fin, String nomClient, String nomPrest) {
        this.id_offre = id_offre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_client = id_client;

    }

    public Reservation() {
    }

}
