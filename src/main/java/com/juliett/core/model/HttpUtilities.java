package com.juliett.core.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import com.xurpas.development.core.logger.Logger;

/**
 * HttpUtilities
 */
public class HttpUtilities {

    private final String REQUEST_BASE_URL;
    private final Map<String, String> REQUEST_HEADERS;
    private final String REQUEST_METHOD;
    private final Logger logger;

    public HttpUtilities(String REQUEST_BASE_URL, Map<String, String> REQUEST_HEADERS, String REQUEST_METHOD,
            Logger logger) {
        this.REQUEST_BASE_URL = REQUEST_BASE_URL;
        this.REQUEST_HEADERS = REQUEST_HEADERS;
        this.REQUEST_METHOD = REQUEST_METHOD;
        this.logger = logger;
    }

    public Logger getLogger() {

        return this.logger;
    }

    public String getREQUEST_BASE_URL() {
        return this.REQUEST_BASE_URL;
    }

    public Map<String, String> getREQUEST_HEADERS() {
        return this.REQUEST_HEADERS;
    }

    public String getREQUEST_METHOD() {
        return this.REQUEST_METHOD;
    }

    @Override
    public String toString() {
        return "{" +
                " REQUEST_BASE_URL='" + getREQUEST_BASE_URL() + "'" +
                ", REQUEST_HEADERS='" + getREQUEST_HEADERS() + "'" +
                ", REQUEST_METHOD='" + getREQUEST_METHOD() + "'" +
                "}";
    }

    public HttpURLConnection sendRequest() throws IOException {

        HttpURLConnection response = null;
        URL url;

        try {

            url = new URL(REQUEST_BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);

            REQUEST_HEADERS.forEach((key, value) -> {

                connection.setRequestProperty(key, key);

            });

            if (connection.getResponseCode() != 200) {
                response = connection;
                throw new RuntimeException("HTTP POST Request Failed with Error code : "
                        + connection.getResponseCode());
            }

            response = connection;
            connection.disconnect();

        } catch (Exception e) {
//        	e.printStackTrace();
            logger.error(e.toString());

        } finally {
            if (response != null) {
                response.disconnect();
            }
        }

        return response;
    }

}