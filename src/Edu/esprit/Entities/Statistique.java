package Edu.esprit.Entities;

import Edu.esprit.DAO.StatistiqueDAO;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



/**
 *
 * @author manel
 */
public class Statistique {

    int nombreClient;//-Nombre des Clients
    int nombrePrestataire;//-Nombre des prestataire
    int nombreOffre;//-Nombre des  Offre
    int nombreHomme;
        int nombreFemme;

//----------------------------------------------------------------------------------------------------------------------------------------
    public DefaultPieDataset clientPrestatairePie() {
        /**/
        DefaultPieDataset data = new DefaultPieDataset();
        StatistiqueDAO GetNombre = new StatistiqueDAO();
        /*---Recuperation du  Nombre des prestataire & Client dans la  base---*/
        nombreClient = GetNombre.findNumberOf("client");
        nombrePrestataire = GetNombre.findNumberOf("prestataire");
        //----------------------------------------------------
        data.setValue("Client", nombreClient);
        data.setValue("Prestataire", nombrePrestataire);
        //----------------------------------------------------
        return data;

    }
  public XYSeriesCollection clientPrestataireXY() {
        StatistiqueDAO GetNombre = new StatistiqueDAO();
        /*---Recuperation du  Nombre des prestataire & Client dans la  base---*/
        nombreClient = GetNombre.findNumberOf("client");
        nombrePrestataire = GetNombre.findNumberOf("prestataire");
        /**/
        // Create a simple XY chart 
        XYSeries series = new XYSeries("Client Prestataire");
        series.add(1, nombreClient);
        series.add(2, nombrePrestataire);
        // Add the series to your data set   
        XYSeriesCollection datasetXY = new XYSeriesCollection();
        datasetXY.addSeries(series);

        return datasetXY;

    }


    public DefaultCategoryDataset clientPrestataireBar() {
        StatistiqueDAO GetNombre = new StatistiqueDAO();
        /*---Recuperation du  Nombre des prestataire & Client dans la  base---*/
        nombreClient = GetNombre.findNumberOf("client");
        nombrePrestataire = GetNombre.findNumberOf("prestataire");
        /**/
        DefaultCategoryDataset datasetBar = new DefaultCategoryDataset();
        datasetBar.setValue(nombreClient, "Client", "Client");
        datasetBar.setValue(nombrePrestataire, "Prestataire", "Prestataire");

        return datasetBar;

    }

//----------------------------------------------------------------------------------------------------------------------------------------
    public DefaultPieDataset prestataireStatistique(int id) {
        //----------------------------------------------------
        //----------------------------------------------------*/
        StatistiqueDAO GetNombre = new StatistiqueDAO();
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("", GetNombre.findNombrePrestatair(id));
         //----------------------------------------------------
        return data;

    }
//----------------------------------------------------------------------------------------------------------------------------------------

    public DefaultPieDataset hommeFemmePie() {
        
        
        StatistiqueDAO GetNombre = new StatistiqueDAO();
        DefaultPieDataset data = new DefaultPieDataset();
        nombreFemme = GetNombre.UserSexe("F");
        nombreHomme = GetNombre.UserSexe("M");
        data.setValue("Nombre de femme", nombreFemme);
        data.setValue("Nombre d'homme", nombreHomme);

        return data;
    }
    
      public XYSeriesCollection hommeFemmeXY() {
        int nombreHomme;
        int nombreFemme;

        StatistiqueDAO GetNombre = new StatistiqueDAO();
        XYSeries series = new XYSeries("Client Prestataire");
        XYSeriesCollection data = new XYSeriesCollection();
        nombreFemme = GetNombre.UserSexe("F");
        nombreHomme = GetNombre.UserSexe("H");
        series.add(1, nombreHomme);
        series.add(5, nombreFemme);
        data.addSeries(series);
        return data;
    }

  

    public DefaultCategoryDataset hommeFemmeBar() {
        int nombreHomme;
        int nombreFemme;
        StatistiqueDAO GetNombre = new StatistiqueDAO();
        /*---Recuperation du  Nombre des prestataire & Client dans la  base---*/
        nombreFemme = GetNombre.UserSexe("F");
        nombreHomme = GetNombre.UserSexe("M");
        /**/
        DefaultCategoryDataset datasetBar = new DefaultCategoryDataset();
        datasetBar.setValue(nombreFemme, "F", "");
        datasetBar.setValue(nombreHomme, "M", "");

        return datasetBar;

    }
//----------------------------------------------------------------------------------------------------------------------------------------
}
