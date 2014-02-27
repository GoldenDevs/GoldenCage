package edu.esprit.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Amine
 */
public class MyConnection {

    private String user = "root";
    private String pwd = "esprit";
    private String url = "jdbc:mysql://localhost:3306/jdbcconnection";
    // java.sql.Connection
    private Connection connection;
    private static MyConnection instance;

    private MyConnection() {
        try {
            // Chargement du Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Création de l'objet de Connexion
            connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion effectué avec succes ...");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erreur de chargement de driver"+ex.getMessage());
        } catch (SQLException ex){
            System.out.println("probleme d'etablissement de connection"+ex.getMessage());
        }
    }
    
    public static MyConnection getInstance(){
        if (instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    
}
