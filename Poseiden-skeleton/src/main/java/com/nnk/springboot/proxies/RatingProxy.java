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
import com.nnk.springboot.domain.Rating;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RatingProxy {

	private final CustomProperties props;
	private final JwtToken jwtToken;

	public List<Rating> findAll() {
		log.info("findAll");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/rating/list";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Rating>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				new ParameterizedTypeReference<List<Rating>>() {}
			);
		
		return response.getBody();
	}
	
	public Rating save(Rating rating) {
		log.info("save");
		log.debug("rating = " + rating);

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/rating/save";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<?> request = new HttpEntity<>(rating, headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Rating> response = restTemplate.exchange(
				url, 
				HttpMethod.POST, 
				request,
				Rating.class);
		
		return response.getBody();
	}
	
	public Rating findById(Integer id) {
		log.info("findById");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/rating/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Rating> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				Rating.class);
		
		return response.getBody();
	}
	
	public Rating deleteById(Integer id) {
		log.info("save");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/rating/delete/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Rating> response = restTemplate.exchange(
				url, 
				HttpMethod.DELETE, 
				request,
				Rating.class);
		
		return response.getBody();
	}
	
}
