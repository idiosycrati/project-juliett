package com.juliett.core.commons.ApiCall;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Hello world!
 */
public final class ApiCall {
    private ApiCall() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {



String jsonResult= apiCall("http://localhost:8081/alpha-vantage.p.rapidapi.com/"+
   "query?from_currency=BTC&function=CURRENCY_EXCHANGE_RATE&to_currency=USD");

    }

    public static String apiCall(String url) {
        String responseData = null;
        
        try {
            HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
            httpClient.setRequestMethod("GET");

            httpClient.setRequestProperty("X-RapidAPI-Key", "eefd572afcmsh2097a3ea41f5f3bp15b4bfjsnc264f4271864");
            httpClient.setRequestProperty("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com");

            Integer responseCode = httpClient.getResponseCode();
            
            InputStreamReader inputStream = null;
            
            if (responseCode > 299) {
                inputStream = new InputStreamReader(httpClient.getErrorStream());
                throw new Exception("Failed call: "+url);
            } else {
                inputStream = new InputStreamReader(httpClient.getInputStream());
            }
            
            BufferedReader reader = new BufferedReader(inputStream);
            
            String line = null;
            StringBuffer data = new StringBuffer();
            
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            
            responseData = data.toString();
            
            reader.close();
            httpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return responseData;
    }
}
