/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.GUI;

import Edu.esprit.DAO.AdministrateurDAO;
import Edu.esprit.Entities.Administrateur;
import Edu.esprit.utils.CRUD;
import javax.swing.JOptionPane;

/**
 *
 * @author Touch media
 */
public class ModifAdmin extends javax.swing.JFrame {

    /**
     * Creates new form AddAdmin
     */
    public ModifAdmin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtf_login = new javax.swing.JTextField();
        txtf_password = new javax.swing.JTextField();
        txtf_nom = new javax.swing.JTextField();
        txtf_prenom = new javax.swing.JTextField();
        txtf_email = new javax.swing.JTextField();
        txtf_adresse = new javax.swing.JTextField();
        btn_valider_regAdmin = new javax.swing.JButton();
        btn_Annuler_regAdmin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Login");

        jLabel2.setText("Password");

        jLabel3.setText("Prenom");

        jLabel4.setText("Nom");

        jLabel5.setText("Adresse");

        jLabel6.setText("Email");

        txtf_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_loginActionPerformed(evt);
            }
        });

        txtf_adresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_adresseActionPerformed(evt);
            }
        });

        btn_valider_regAdmin.setText("Valider");
        btn_valider_regAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_valider_regAdminActionPerformed(evt);
            }
        });

        btn_Annuler_regAdmin.setText("Annuler");
        btn_Annuler_regAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Annuler_regAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtf_password, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtf_login)
                                .addComponent(txtf_nom)
                                .addComponent(txtf_prenom)
                                .addComponent(txtf_email)
                                .addComponent(txtf_adresse, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_valider_regAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Annuler_regAdmin)
                        .addGap(12, 12, 12)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtf_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtf_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtf_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtf_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtf_adresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_valider_regAdmin)
                    .addComponent(btn_Annuler_regAdmin))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtf_adresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_adresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_adresseActionPerformed

    private void txtf_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_loginActionPerformed

    private void btn_Annuler_regAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Annuler_regAdminActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_Annuler_regAdminActionPerformed

    private void btn_valider_regAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_valider_regAdminActionPerformed
          Administrateur admin=new Administrateur();
          admin=AdministrateurDAO.findAdminByLogin(this.getTitle());
            txtf_login.setText(admin.getLogin());
            txtf_login.setText(admin.getLogin());
            txtf_password.setText(admin.getPassword());
            txtf_nom.setText(admin.getNom());
            txtf_prenom.setText(admin.getPrenom());
            txtf_email.setText(admin.getEmail());
            txtf_adresse.setText(admin.getAdresse());
        if(CRUD.updateUserByLogin(admin)){
            JOptionPane.showMessageDialog(null, "Administrateur "+txtf_login.getText()+" est Mise a jour avec succes");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Erreur de Mise a jour");
        }
            
    }//GEN-LAST:event_btn_valider_regAdminActionPerformed

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
            java.util.logging.Logger.getLogger(ModifAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Annuler_regAdmin;
    private javax.swing.JButton btn_valider_regAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtf_adresse;
    private javax.swing.JTextField txtf_email;
    private javax.swing.JTextField txtf_login;
    private javax.swing.JTextField txtf_nom;
    private javax.swing.JTextField txtf_password;
    private javax.swing.JTextField txtf_prenom;
    // End of variables declaration//GEN-END:variables
}
