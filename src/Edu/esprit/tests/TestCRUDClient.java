/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.tests;

import Edu.esprit.DAO.ClientDAO;
import Edu.esprit.Entities.Client;
import java.sql.Date;

/**
 *
 * @author Elyes
 */
public class TestCRUDClient {
    public static void main(String[] args) {
        Client client =new Client(new java.sql.Date((new java.util.Date()).getTime()), "Client5", "123", "Said", "Saiid", "Rue hahaha", "Said@saiid.tn",new java.sql.Date((new java.util.Date()).getTime()));
        System.out.println(ClientDAO.addClient(client));
    }
}
