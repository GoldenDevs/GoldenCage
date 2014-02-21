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
public class Evaluation {
    private int note;
    private Date dateNote;
    private int id_offre;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Date getDateNote() {
        return dateNote;
    }

    public void setDateNote(Date dateNote) {
        this.dateNote = dateNote;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }
    
    
}
