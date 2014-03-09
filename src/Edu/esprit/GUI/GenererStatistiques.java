/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GestionFormules.java
 *
 * Created on 14 févr. 2014, 11:58:48
 */
package Edu.esprit.GUI;

import Edu.esprit.DAO.StatistiqueDAO;
import Edu.esprit.Entities.Statistique;
import Edu.esprit.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import javax.sound.midi.SysexMessage;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author manel
 */
public class GenererStatistiques extends javax.swing.JFrame {

    /**
     * Creates new form MenuGestionDepot
     */
    public GenererStatistiques() {
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

        canvas1 = new java.awt.Canvas();
        clientPrestataire = new javax.swing.JButton();
        typeStatistique = new javax.swing.JComboBox();
        hommeFemme = new javax.swing.JButton();
        graphiqueChart = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        clientPrestataire.setText("Client/prestataire");
        clientPrestataire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientPrestataireActionPerformed(evt);
            }
        });

        typeStatistique.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pie", "Bar", "XY" }));
        typeStatistique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeStatistiqueActionPerformed(evt);
            }
        });

        hommeFemme.setText("Homme/Femme");
        hommeFemme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hommeFemmeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout graphiqueChartLayout = new javax.swing.GroupLayout(graphiqueChart);
        graphiqueChart.setLayout(graphiqueChartLayout);
        graphiqueChartLayout.setHorizontalGroup(
            graphiqueChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
        );
        graphiqueChartLayout.setVerticalGroup(
            graphiqueChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Nombre d'inscription par mois ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hommeFemme, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientPrestataire, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(typeStatistique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)))
                .addGap(90, 90, 90)
                .addComponent(graphiqueChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(typeStatistique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientPrestataire)
                .addGap(45, 45, 45)
                .addComponent(hommeFemme)
                .addGap(52, 52, 52)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(73, 73, 73))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphiqueChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientPrestataireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientPrestataireActionPerformed
        // TODO add your handling code here:

        Statistique stat = new Statistique();

        StatistiqueDAO GetNombre = new StatistiqueDAO();

        switch (typeStatistique.getSelectedIndex()) {
            case 0:
                DefaultPieDataset pieDataset = new DefaultPieDataset(); //Dataset qui va contenir les Données 
                //remplissage du  dataset du pie
                pieDataset = stat.clientPrestatairePie();
                System.out.println("" + pieDataset);
                // Creation du fromage 
                JFreeChart chartPie = ChartFactory.createPieChart3D("Client/prestataire", pieDataset, true, true, true);            //Graphe
                saveChart(chartPie);
                //affichage
                ChartPanel frame = new ChartPanel(chartPie);
                graphiqueChart.add(frame);
                frame.setSize(600, 500);
                this.setVisible(true);

                break;
 case 1:
      
                // Create a simple Bar chart       
                DefaultCategoryDataset datasetBar = new DefaultCategoryDataset();
                //remplissage du  dataset du pie
                datasetBar = stat.clientPrestataireBar();
                System.out.println("" + datasetBar);
                JFreeChart chartBar = ChartFactory.createBarChart3D("Prestataire VS Client","", "", datasetBar, PlotOrientation.VERTICAL, true, true, true);
                saveChart(chartBar);
                //affichage
                ChartPanel frameBar = new ChartPanel(chartBar);
                graphiqueChart.add(frameBar);
                frameBar.setSize(600, 500);
                this.setVisible(true);
                break;
 case 2:
      // Add the series to your data set   
                XYSeriesCollection datasetXY = new XYSeriesCollection();
                datasetXY = stat.clientPrestataireXY();
                // Generate the graph       
                JFreeChart chartXY = ChartFactory.createTimeSeriesChart("Client/Prestataire", "Client", "Prestataire", datasetXY, true, true, true);
                saveChart(chartXY);
                //affichage
                ChartPanel frameXY = new ChartPanel(chartXY);
                graphiqueChart.add(frameXY);
                frameXY.setSize(600, 500);
                this.setVisible(true);
                break;

        }


    }//GEN-LAST:event_clientPrestataireActionPerformed

    private void hommeFemmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hommeFemmeActionPerformed
        // TODO add your handling code here:
        Statistique stat = new Statistique();

        StatistiqueDAO GetNombre = new StatistiqueDAO();

        // choix entre  les type de  statistique
        switch (typeStatistique.getSelectedIndex()) {
            case 0:
                DefaultPieDataset pieDataset = new DefaultPieDataset();
                //remplissage du  dataset du pie
                pieDataset = stat.hommeFemmePie();
                // Creation du fromage hommeFemmePie
                JFreeChart chart = ChartFactory.createPieChart3D("femme/homme",pieDataset, true, true, true);
    saveChart(chart);
                //affichage
                ChartPanel frame = new ChartPanel(chart);
                graphiqueChart.add(frame);
                frame.setSize(600, 500);
                this.setVisible(true);
                break;

                       
          
            case 1:
                // Create a simple Bar chart       
                DefaultCategoryDataset datasetBar = new DefaultCategoryDataset();
                //remplissage du  dataset du pie
                datasetBar = stat.hommeFemmeBar();
                System.out.println("" + datasetBar);
                JFreeChart chartBar = ChartFactory.createBarChart3D("Homme/Femme", "M", "F", datasetBar, PlotOrientation.VERTICAL, true, true, true);
                saveChart(chartBar);
                //affichage
                ChartPanel frameBar = new ChartPanel(chartBar);
                graphiqueChart.add(frameBar);
                frameBar.setSize(600, 500);
                this.setVisible(true);
                break;
        
        case 2:
               // Add the series to your data set   
                XYSeriesCollection datasetXY = new XYSeriesCollection();
                datasetXY = stat.hommeFemmeXY();
                // Generate the graph       
                JFreeChart chartXY = ChartFactory.createTimeSeriesChart("Homme Femme", "M", "Femme", datasetXY, true, true, true);
                saveChart(chartXY);
                //affichage
                ChartPanel frameXY = new ChartPanel(chartXY);
                graphiqueChart.add(frameXY);
                frameXY.setSize(600, 500);
                this.setVisible(true);

                break;

    }//GEN-LAST:event_hommeFemmeActionPerformed
    }
    private void typeStatistiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeStatistiqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeStatistiqueActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        StatistiqueDAO aO = new StatistiqueDAO();
        java.util.Date dd = jDateChooser1.getCalendar().getTime();
        java.sql.Date ss = new Date(dd.getTime());
       java.util.Date dd1 = jDateChooser2.getCalendar().getTime();
        java.sql.Date ss1 = new Date(dd1.getTime());
        System.out.println(aO.listesjour(ss.toString(), ss1.toString()).size());
         
        
                XYSeriesCollection datasetXY = new XYSeriesCollection();
                XYSeries series = new XYSeries("hello");
                 for(Integer a : aO.listesjour(ss.toString(), ss1.toString()))
        {
        series.add(aO.listesjour(ss.toString(), ss1.toString()).indexOf(a),a);
        }
                 datasetXY.addSeries(series);
                // Generate the graph       
                JFreeChart chartXY = ChartFactory.createTimeSeriesChart("ClientInscrit/jour", "Jour", "NombreClientInscrit", datasetXY, true, true, true);
                saveChart(chartXY);
                //affichage
                ChartPanel frameXY = new ChartPanel(chartXY);
                graphiqueChart.add(frameXY);
                frameXY.setSize(600, 500);
                this.setVisible(true);
       
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new GenererStatistiques().setVisible(true);
            }
        });
    }

    public void saveChart(JFreeChart chart) {
        String fileName = "E:/chart.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG First Parameter:
             * FileName Second Parameter: Chart To Save Third Parameter: Height
             * Of Picture Fourth Parameter: Width Of Picture
             */

            ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Problem occurred creating chart.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JButton clientPrestataire;
    private javax.swing.JPanel graphiqueChart;
    private javax.swing.JButton hommeFemme;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public com.toedter.calendar.JDateChooser jDateChooser1;
    public com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JComboBox typeStatistique;
    // End of variables declaration//GEN-END:variables

}
