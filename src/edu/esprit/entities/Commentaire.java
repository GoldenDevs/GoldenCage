/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.entities;

/**
 *
 * @author Elyes
 */
public class Commentaire {
    private String idCom;
    private String id_offre;
    private String id_client;
    private String text;
    private String date_com;

    public Commentaire() {
    }

    public String getIdCom() {
        return idCom;
    }

    public void setIdCom(String idCom) {
        this.idCom = idCom;
    }

    public String getId_offre() {
        return id_offre;
    }

    public void setId_offre(String id_offre) {
        this.id_offre = id_offre;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate_com() {
        return date_com;
    }

    public void setDate_com(String date_com) {
        this.date_com = date_com;
    }

    public String toString() {
        return "Commenataire{" + "idCom=" + idCom + ", id_offre=" + id_offre + ", id_client=" + id_client + ", text=" + text + ", date_com=" + date_com + '}';
    }
    
    
}
