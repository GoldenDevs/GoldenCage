/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.Entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Touch media
 */
public class Offre {
    private int id_Offre;
    private String libelle_off;
    private boolean stat_offre;
    private Date date_Post;
    private String nomPrest;
    private List<Evaluation>noteOffre;
    private List<Commentaire>listCommentaires;

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

    public boolean isStat_offre() {
        return stat_offre;
    }

    public void setStat_offre(boolean stat_offre) {
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

    public List<Evaluation> getNoteOffre() {
        return noteOffre;
    }

    public void setNoteOffre(List<Evaluation> noteOffre) {
        this.noteOffre = noteOffre;
    }

    public List<Commentaire> getListCommentaires() {
        return listCommentaires;
    }

    public void setListCommentaires(List<Commentaire> listCommentaires) {
        this.listCommentaires = listCommentaires;
    }

    public Offre(int id_Offre, String libelle_off, boolean stat_offre, Date date_Post, String nomPrest) {
        this.id_Offre = id_Offre;
        this.libelle_off = libelle_off;
        this.stat_offre = stat_offre;
        this.date_Post = date_Post;
        this.nomPrest = nomPrest;
    }

    public Offre() {
    }
    
    
}
