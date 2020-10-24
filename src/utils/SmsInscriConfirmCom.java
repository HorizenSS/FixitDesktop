/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.scene.control.Alert;

public class SmsInscriConfirmCom {
    public String sendSms(int s) {
		try {
			// Construct data
			String apiKey = "apikey=" + "QadtuUxVEd8-oeF6mEE6u4fUTkN0T2zRos9aN9Uzer";
			String message = "&message=" + "Commande Confirmée! \n Merci pour votre Confiance. ";
			String sender = "&sender=" + "khouloud";
			String numbers = "&numbers=" + "216"+s;
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                            Alert alert = new InputValidation().getAlert("Succes", "Vous êtes inscrit !");
                            alert.showAndWait();
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (IOException e) {
			System.out.println("Error SMS "+e);
		}
        return null;
        
    
}
}
