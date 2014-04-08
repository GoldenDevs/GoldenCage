/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.midlet;

/**
 *
 * @author Elyes
 */
public class Offre {
    private String id_Offre;
    private String libelle_off;
    private String stat_offre;
    private String date_post;
    private String nomPrest;
    private String prix;    
    private String noteOffre;
    private String urlimg;
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
    public Offre() {
    }

    public String getDate_post() {
        return date_post;
    }

    public void setDate_post(String date_post) {
        this.date_post = date_post;
    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }

  
    
    public String getId_Offre() {
        return id_Offre;
    }

    public void setId_Offre(String id_Offre) {
        this.id_Offre = id_Offre;
    }

    public String getLibelle_off() {
        return libelle_off;
    }

    public void setLibelle_off(String libelle_off) {
        this.libelle_off = libelle_off;
    }

    public String getStat_offre() {
        return stat_offre;
    }

    public void setStat_offre(String stat_offre) {
        this.stat_offre = stat_offre;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getNomPrest() {
        return nomPrest;
    }

    public void setNomPrest(String nomPrest) {
        this.nomPrest = nomPrest;
    }

    public String getNoteOffre() {
        return noteOffre;
    }

    public void setNoteOffre(String noteOffre) {
        this.noteOffre = noteOffre;
    }

    public String toString() {
        return "Offre{" + "id_Offre=" + id_Offre + ", libelle_off=" + libelle_off + ", stat_offre=" + stat_offre + ", date_post=" + date_post + ", nomPrest=" + nomPrest + ", prix=" + prix + ", noteOffre=" + noteOffre + ", urlimg=" + urlimg + '}';
    }
    
    
}
