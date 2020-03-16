/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author ACER
 */
public class ApiConnection {

    public static HttpURLConnection con ;
    public void makeConnection() {
        BufferedReader reader ;
        String line ;
        StringBuffer responseLine = new StringBuffer();
        try
        {
            
               URL url = new URL("https://jsonplaceholder.typicode.com/albums");
               con= (HttpURLConnection) url.openConnection();
               con.setRequestMethod("GET");
            
               int status = con.getResponseCode();
               System.out.println(status);
        }

    catch(Exception MalformedURLException)
        {
            System.out.println(MalformedURLException);

        }
     
    }
    public static void main(String[] args) {
        ApiConnection ap = new ApiConnection();
        ap.makeConnection();
    }
}
