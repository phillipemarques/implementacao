/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.myexperiment.core;

import br.ufjf.myexperiment.exception.MyExperimentException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author vitorfs
 */
public abstract class MyExperimentBaseClient {
    private String baseUri;
    
    public MyExperimentBaseClient() {
    
    }
    
    public MyExperimentBaseClient(String baseUri) {
        this.baseUri = baseUri;
    }
    
    protected HttpURLConnection request(String endpoint, String method, int expectedResponseCode) throws MyExperimentException {
        return request(endpoint, method, expectedResponseCode, null, null);
    }

    protected HttpURLConnection request(String endpoint, String method, int expectedResponseCode, String acceptData) throws MyExperimentException {
        return request(endpoint, method, expectedResponseCode, acceptData, null);
    }
  
    protected HttpURLConnection request(String endpoint, String method, int expectedResponseCode, String acceptData, String contentType) throws MyExperimentException {
        HttpURLConnection connection = null;
        
        try {            
            String uri = this.getBaseUri() + endpoint;
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestMethod(method);
            
            if (acceptData != null) {
                connection.setRequestProperty("Accept", acceptData);
            }
            
            if (contentType != null) {
                connection.setRequestProperty("Content-Type", contentType);
            }

            switch (method) {
                case "GET":
                    break;
                case "POST":
                    break;
            }
            
            int responseCode = connection.getResponseCode();
            if (responseCode != expectedResponseCode) {
                throw new MyExperimentException(String.format("Invalid HTTP Response Code. Expected %d, actual %d, URL %s", expectedResponseCode, responseCode, url));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return connection;
    }
    
    protected String parseResponse(HttpURLConnection response) {
        try {
            InputStream responseStream = new BufferedInputStream(response.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = responseStreamReader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }

            responseStreamReader.close();
            String output = stringBuilder.toString();
            responseStream.close();

            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * @return the baseUri
     */
    public String getBaseUri() {
        return baseUri;
    }

    /**
     * @param baseUri the baseUri to set
     */
    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }
    
}
