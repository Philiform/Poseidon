package com.nnk.springboot.proxies;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nnk.springboot.configuration.CustomProperties;
import com.nnk.springboot.domain.JwtToken;
import com.nnk.springboot.domain.Trade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TradeProxy {

	private final CustomProperties props;
	private final JwtToken jwtToken;

	public List<Trade> findAll() {
		log.info("findAll");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/trade/list";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Trade>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				new ParameterizedTypeReference<List<Trade>>() {}
			);
		
		return response.getBody();
	}
	
	public Trade save(Trade trade) {
		log.info("save");
		log.debug("trade = " + trade);

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/trade/save";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<?> request = new HttpEntity<>(trade, headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Trade> response = restTemplate.exchange(
				url, 
				HttpMethod.POST, 
				request,
				Trade.class);
		
		return response.getBody();
	}
	
	public Trade findById(Integer id) {
		log.info("findById");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/trade/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Trade> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				Trade.class);
		
		return response.getBody();
	}
	
	public Trade deleteById(Integer id) {
		log.info("save");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/trade/delete/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Trade> response = restTemplate.exchange(
				url, 
				HttpMethod.DELETE, 
				request,
				Trade.class);
		
		return response.getBody();
	}
	
}
