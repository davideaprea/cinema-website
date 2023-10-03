package com.epicode.Spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epicode.Spring.security.entity.Receipt;
import com.epicode.Spring.security.payload.ReceiptDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PayPalService {
	@Value("${paypal.clientId}")
    private String clientId;
	
	@Value("${paypal.secret}")
    private String clientSecret;
	
	private final String apiBaseUrl="https://api-m.sandbox.paypal.com";
	
	@Autowired private AuthServiceImpl userService;
	@Autowired private EmailService emailService;
	
	private String getAccessToken() {
		try {
			final String tokenApi=apiBaseUrl+"/v1/oauth2/token";
			
			HttpHeaders headers = new HttpHeaders();
	        headers.setBasicAuth(clientId, clientSecret);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			
	        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);
	        ResponseEntity<String> response = new RestTemplate().exchange(tokenApi, HttpMethod.POST, request, String.class);
	        
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(response.getBody());
			return jsonNode.get("access_token").asText();
		} catch (JsonProcessingException e) {
			throw new DataIntegrityViolationException("Couldn't get the access token.");
		}
	}
	
	public String createOrder(ReceiptDto r) {
		
		try {
			Receipt receipt=new Receipt();
			receipt.setBookings(r.getBookings());
			receipt.setUser(userService.getUserByEmailOrPassword(r.getUser().getUsername()));
			String accessToken=getAccessToken();
			
			final String url=apiBaseUrl+"/v2/checkout/orders";
			
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(accessToken);
	        
	        String body = "{\n" +
	                "  \"intent\": \"CAPTURE\",\n" +
	                "  \"purchase_units\": [\n" +
	                "    {\n" +
	                "      \"amount\": {\n" +
	                "        \"currency_code\": \"EUR\",\n" +
	                "        \"value\": \"" + receipt.getTotPrice() + "\"\n" +
	                "      }\n" +
	                "    }\n" +
	                "  ]\n" +
	                "}";
	        
	        HttpEntity<String> request = new HttpEntity<String>(body, headers);
	        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);

	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(response.getBody());
	        System.out.println(jsonNode);
			return jsonNode.get("id").asText();
		}catch (JsonProcessingException e) {
			throw new DataIntegrityViolationException("Error creating the order.");
		}
	}
	
	public String captureOrder(String orderId) {
		String accessToken=getAccessToken();
		final String url=apiBaseUrl+"/v2/checkout/orders/"+orderId+"/capture";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(accessToken);
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);

//	        ObjectMapper objectMapper = new ObjectMapper();
//	        JsonNode jsonNode = objectMapper.readTree(response.getBody());
//	        String emailAddress = jsonNode.at("/payment_source/paypal/email_address").asText();
//	        emailService.sendPaymentConfirmation(emailAddress);
		
		return response.getBody();
	}
}
