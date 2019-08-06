package kr.pethub.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtil {
	
	public static String getContent(String url) throws IOException{
		return getContent(url, 5, "utf-8");
	}
	
	
	public static String getContent(String url, int timeout) throws IOException{
		return getContent(url, timeout, "utf-8");
	}
	
	public static String getContent(String url, int timeout, String charSet) throws IOException{
		
		CloseableHttpClient httpClient = null;
		StringBuffer response = null;
		BufferedReader reader = null;
		
	    try {
		
			//http client 생성
	        httpClient = HttpClients.createDefault();
	 
	        //get 메서드와 URL 설정
	        HttpGet httpGet = new HttpGet(url);
	 
	        //agent 정보 설정
	        httpGet.addHeader("User-Agent", "Mozila/5.0");
	        
	        RequestConfig requestConfig = RequestConfig.custom()
	        		  .setSocketTimeout(timeout * 1000)
	        		  .setConnectTimeout(timeout * 1000)
	        		  .setConnectionRequestTimeout(timeout * 1000)
	        		  .build();
	        httpGet.setConfig(requestConfig);

	        //get 요청
	        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
	        
	        //response의 status 코드 출력
	        if( httpResponse.getStatusLine().getStatusCode() == 200) {
	        	 reader = new BufferedReader( new InputStreamReader(httpResponse.getEntity().getContent(), charSet) );
	        	 
	 	        String inputLine;
	 	        response = new StringBuffer();
	 	 
	 	        while ((inputLine = reader.readLine()) != null) {
	 	            response.append(inputLine);
	 	        }
	        }
	        
	    }catch(Exception e) {
	    	e.getStackTrace();
	    }finally {
	    	if(reader != null) reader.close();
	    	if(httpClient != null)  httpClient.close();
		}
	    
	    return response.toString();
	    
	}
	
	
}
