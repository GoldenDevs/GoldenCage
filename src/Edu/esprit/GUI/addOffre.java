/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.GUI;

import Edu.esprit.DAO.OffreDAO;
import Edu.esprit.DAO.PrestDAO;
import Edu.esprit.Entities.Offre;
import Edu.esprit.Entities.Prestataire;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Elyes
 */
public class addOffre extends javax.swing.JFrame {
    private String basePath="C:\\Images";
    FileInputStream fin ;
    /**
     * Creates new form addOffre
     */
    public addOffre() {
        
        initComponents();
        List<Prestataire>list=new ArrayList<Prestataire>();
        list=PrestDAO.listPrest();
        for(Prestataire p:list){
            cbx_liste_prest.addItem(p.getLogin().toString());
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_libelle_offre = new javax.swing.JLabel();
        lbl_prest_offre = new javax.swing.JLabel();
        lbl_prix_offre = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        txtf_Libelle_offre = new javax.swing.JTextField();
        txtf_prix_offre = new javax.swing.JTextField();
        cbx_liste_prest = new javax.swing.JComboBox();
        btn_v_ajout_offre = new javax.swing.JButton();
        btn_annuler_ajout_offre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_libelle_offre.setText("Libelle :");

        lbl_prest_offre.setText("Prestataire :");

        lbl_prix_offre.setText("Prix :");

        lbl_image.setForeground(new java.awt.Color(255, 255, 204));
        lbl_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_imageMouseClicked(evt);
            }
        });
        lbl_image.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lbl_imageKeyPressed(evt);
            }
        });

        cbx_liste_prest.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        btn_v_ajout_offre.setText("Valider");
        btn_v_ajout_offre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_v_ajout_offreActionPerformed(evt);
            }
        });

        btn_annuler_ajout_offre.setText("Annuler");
        btn_annuler_ajout_offre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annuler_ajout_offreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(btn_v_ajout_offre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_annuler_ajout_offre))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_libelle_offre, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_prix_offre)
                                .addComponent(lbl_prest_offre))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbx_liste_prest, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtf_prix_offre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtf_Libelle_offre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_libelle_offre)
                    .addComponent(txtf_Libelle_offre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_prest_offre)
                    .addComponent(cbx_liste_prest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_prix_offre)
                    .addComponent(txtf_prix_offre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_v_ajout_offre)
                    .addComponent(btn_annuler_ajout_offre))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_annuler_ajout_offreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annuler_ajout_offreActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_btn_annuler_ajout_offreActionPerformed

    private void btn_v_ajout_offreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_v_ajout_offreActionPerformed
        Offre o=new Offre();
        o.setLibelle_off(txtf_Libelle_offre.getText());
        o.setPrix(Float.parseFloat(txtf_prix_offre.getText()));
        o.setNomPrest(cbx_liste_prest.getSelectedItem().toString());
        OffreDAO.addOffre(o);
    }//GEN-LAST:event_btn_v_ajout_offreActionPerformed

    private void lbl_imageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_imageKeyPressed
       
        
    }//GEN-LAST:event_lbl_imageKeyPressed

    private void lbl_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_imageMouseClicked
        JFileChooser browser = new JFileChooser(basePath);
       browser.setFileFilter(new JPEGImageFileFilter());
       int res = browser.showOpenDialog(null);
       lbl_image.setIcon(new ImageIcon(browser.getSelectedFile().getAbsolutePath()));
        try {
            fin = new FileInputStream(browser.getSelectedFile().getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(addOffre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_lbl_imageMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(addOffre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addOffre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addOffre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addOffre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addOffre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_annuler_ajout_offre;
    private javax.swing.JButton btn_v_ajout_offre;
    private javax.swing.JComboBox cbx_liste_prest;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_libelle_offre;
    private javax.swing.JLabel lbl_prest_offre;
    private javax.swing.JLabel lbl_prix_offre;
    private javax.swing.JTextField txtf_Libelle_offre;
    private javax.swing.JTextField txtf_prix_offre;
    // End of variables declaration//GEN-END:variables
 public class JPEGImageFileFilter extends FileFilter implements java.io.FileFilter{
        public boolean accept(File f){
                if (f.getName().toLowerCase().endsWith(".png")) return true;
                if (f.getName().toLowerCase().endsWith(".jpeg")) return true;
                if (f.getName().toLowerCase().endsWith(".jpg")) return true;
                
                if(f.isDirectory())return true;
                return false;
       }
        public String getDescription(){
          return "JPEG files";
        }

} 
 
}
