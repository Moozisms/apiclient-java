package net.moozisms.mavenproject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {


    public static void main(String[] args) {

        String targetURL = "http://api.moozisms.com";//?api_key=123456&api_secret=123456&to=22892520119&from=MOM&text=cc ici&datatype=json";
        try {
            String api_key = "123456";
            String api_secret = "123456";
            String to = "22890286201";
            String from = "MOOZISMS"; // 11 caracteres au maximum
            String text = "Ceci est un sms test de la plateforme de MOOZISMS";
            String datatype = "json";  //String datatype = "xml";
            String urlParameters = "api_key="+api_key+
                    "&api_secret="+api_secret+
                    "&to="+to+
                    "&from="+from+
                    "&text="+text+
                    "&datatype="+datatype; 
            System.out.println("param "+urlParameters);
            //String params = "api_key=123456&api_secret=123456&to=22892520119&from=MOM&text=cc ici&datatype=json";
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            URL restServiceURL = new URL(targetURL);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setInstanceFollowRedirects(false);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConnection.setRequestProperty("charset", "utf-8");
            httpConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            httpConnection.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
                wr.write(postData);
            }

            int responseCode = httpConnection.getResponseCode();
            String responseMessage = httpConnection.getResponseMessage();

            System.out.println("Response code : " + responseCode + " Response message :" + responseMessage);
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));

            String buffLine;
            String output = "";
            System.out.println("Output from Server:  \n");

            while ((buffLine = responseBuffer.readLine()) != null) {
                //System.out.println(buffLine);
                output += buffLine;
            }
            System.out.println("Response du serveur : \n"+output);
            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
