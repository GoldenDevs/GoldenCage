/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Elyes
 */
public class Images {
    
    
    
    public static String downloadImage(int id_offre){
        String path;
        FileOutputStream fout=null;
        String requete="Select img From offre where id_offre=?";
        try{
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id_offre);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Blob b = rs.getBlob(1);
            byte[] bt = new byte[(int) b.length()];
            bt = b.getBytes(1, (int)b.length());
            path="C:\\Image\\PhotoOffreID"+id_offre+".jpg";
            fout = new FileOutputStream(path);
            fout.write(bt);
            fout.close();
            JOptionPane.showMessageDialog(null, "Image Downloaded : "+path);
            return path;
            
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
	}
    }
    
    public static boolean uploadImage(String id,String path){ 
        String requete="Update offre set img=? where libelle_offre=?";
        FileInputStream fin;
        try{
		PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
                
		fin = new FileInputStream(path);
                ps.setBinaryStream(1, fin, fin.available());
		ps.setString(2, id);
		ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Image Uploaded");
		return true;
		}catch(Exception e){
			e.printStackTrace();
                        return false;
		}
    }
}
