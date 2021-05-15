/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.payment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.ws.rs.WebApplicationException;

/**
 * @author peter
 */
public interface Payment {

    public void pay() throws WebApplicationException;

    default HttpURLConnection getUrlConnection(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "server");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        return connection;
    }

}
