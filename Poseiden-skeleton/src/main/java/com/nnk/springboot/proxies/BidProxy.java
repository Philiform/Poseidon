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
import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.domain.JwtToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BidProxy {

	private final CustomProperties props;
	private final JwtToken jwtToken;

	public List<Bid> findAll() {
		log.info("findAll");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/bid/list";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Bid>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				new ParameterizedTypeReference<List<Bid>>() {}
			);
		
		return response.getBody();
	}
	
	public Bid save(Bid bid) {
		log.info("save");
		log.debug("bid = " + bid);

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/bid/save";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<?> request = new HttpEntity<>(bid, headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Bid> response = restTemplate.exchange(
				url, 
				HttpMethod.POST, 
				request,
				Bid.class);
		
		return response.getBody();
	}
	
	public Bid findById(Integer id) {
		log.info("findById");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/bid/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Bid> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				Bid.class);
		
		return response.getBody();
	}
	
	public Bid deleteById(Integer id) {
		log.info("save");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/bid/delete/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Bid> response = restTemplate.exchange(
				url, 
				HttpMethod.DELETE, 
				request,
				Bid.class);
		
		return response.getBody();
	}
	
}
