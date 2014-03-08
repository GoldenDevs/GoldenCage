/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edu.esprit.tests;

import Edu.esprit.utils.Images;

/**
 *
 * @author Elyes
 */
public class TestImages {
    public static void main(String[] args) {
        Images.uploadImage("Cat", "C:\\Image\\offre.jpg");
        String path=Images.downloadImage(1);
        System.out.println(path);
        
    }
}
