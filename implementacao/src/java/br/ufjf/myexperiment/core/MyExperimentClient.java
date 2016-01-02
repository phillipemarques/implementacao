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
import br.ufjf.myexperiment.model.Search;
import java.net.HttpURLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author vitorfs
 * @author phillipe
 */
public class MyExperimentClient extends MyExperimentBaseClient implements MyExperimentServices {
private final String queryTime = "&type=file,pack,workflow&elements=title,created-at,updated-at,resource,id,uri,statistics,"
        + "uploader,description,type,preview,thumbnail,thumbnail-big,svg,license-type,content-uri,"
        + "content-type,content,tags,filename,name,email,avatar,city,country";

    public Search search(String query) throws MyExperimentException {
        String url = "/search.xml?query=" + query + queryTime;
        HttpURLConnection response = request(url, "GET", 200);
        Search search = null;
	 try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Search.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		search = (Search) jaxbUnmarshaller.unmarshal(response.getInputStream());
	  } catch (Exception e) {
		e.printStackTrace();
	  }
         return search;
    }
    
        public Search search(String query,String queryTime) throws MyExperimentException {
        String url = "/search.xml?query=" + query + queryTime;
        HttpURLConnection response = request(url, "GET", 200);
        Search search = null;
	 try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Search.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		search = (Search) jaxbUnmarshaller.unmarshal(response.getInputStream());
	  } catch (Exception e) {
		e.printStackTrace();
	  }
         return search;
    }

    
}
