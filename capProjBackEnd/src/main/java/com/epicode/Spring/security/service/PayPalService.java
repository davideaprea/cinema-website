package com.epicode.Spring.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epicode.Spring.security.entity.Receipt;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PayPalService {
	@Value("${paypal.clientId}")
    private String clientId;
	
	@Value("${paypal.secret}")
    private String clientSecret;
	
	private final String apiBaseUrl="https://api-m.sandbox.paypal.com";
	
	public String getAccessToken() throws Exception {
		final String tokenApi=apiBaseUrl+"/v1/oauth2/token";
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(clientId, clientSecret);
        
        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);
        ResponseEntity<String> response = new RestTemplate().exchange(tokenApi, HttpMethod.POST, request, String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        return jsonNode.get("access_token").asText();
	}
	
	public void createOrder(Receipt r) throws Exception {
		String accessToken=getAccessToken();
		final String url=apiBaseUrl+"v2/checkout/orders";
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        
        HttpEntity<Receipt> request = new HttpEntity<Receipt>(r, headers);
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);
	}
	
	public JsonNode capturePayment(long orderId) throws Exception {
		String accessToken=getAccessToken();
		final String url=apiBaseUrl+"v2/checkout/orders/"+orderId+"/capture";
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.getBody());
	}
}
