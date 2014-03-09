/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Touch media
 */
public class Offre {
    private int id_Offre;
    private String libelle_off;
    private boolean stat_offre;
    private float prix;
    private Date date_Post;
    private String nomPrest;
    private float noteOffre;
    private List<Commentaire>listCommentaires;

     public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
  
      public float getNoteOffre() {
        return noteOffre;
    }

    public void setNoteOffre(float noteOffre) {
        this.noteOffre = noteOffre;
    }
    public int getId_Offre() {
        return id_Offre;
    }

    public void setId_Offre(int id_Offre) {
        this.id_Offre = id_Offre;
    }

    public String getLibelle_off() {
        return libelle_off;
    }

    public void setLibelle_off(String libelle_off) {
        this.libelle_off = libelle_off;
    }

    public boolean getEtatoffre() {
        return stat_offre;
    }

    public void setEtat_offre(boolean stat_offre) {
        this.stat_offre = stat_offre;
    }

    public Date getDate_Post() {
        return date_Post;
    }

    public void setDate_Post(Date date_Post) {
        this.date_Post = date_Post;
    }

    public String getNomPrest() {
        return nomPrest;
    }

    public void setNomPrest(String nomPrest) {
        this.nomPrest = nomPrest;
    }

    

    public List<Commentaire> getListCommentaires() {
        return listCommentaires;
    }

    public void setListCommentaires(List<Commentaire> listCommentaires) {
        this.listCommentaires = listCommentaires;
    }

    public Offre(String libelle_off, boolean stat_offre, float prix, String nomPrest) {
        this.libelle_off = libelle_off;
        this.stat_offre = stat_offre;
        this.prix = prix;
        this.date_Post = new Date(new java.util.Date().getTime());
        this.nomPrest = nomPrest;
    }

    
    

    public Offre() {
        this.stat_offre=true;
        this.date_Post = new Date(new java.util.Date().getTime());
    }

    @Override
    public String toString() {
        return "Offre{" + "id_Offre=" + id_Offre + ", libelle_off=" + libelle_off + ", stat_offre=" + stat_offre + ", prix=" + prix + ", date_Post=" + date_Post + ", nomPrest=" + nomPrest + ", noteOffre=" + noteOffre + '}';
    }
    
    
}
