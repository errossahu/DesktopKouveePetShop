/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Smith
 */
public class ApiClient  {
    public static String URL = "http://kouveepetshopapi.smithdev.xyz/index.php/"; //create parent url
    Gson gson = new Gson();
    
    URL urlcon;
    HttpURLConnection con;
    
    BufferedReader in;
    String inputLine;
    StringBuffer response = new StringBuffer();
    
    JSONObject RequestResponse;
    JSONArray ResponseListData;
    JSONObject ResponseData;

}
