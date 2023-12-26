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
import com.nnk.springboot.domain.Rule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RuleProxy {

	private final CustomProperties props;
	private final JwtToken jwtToken;

	public List<Rule> findAll() {
		log.info("findAll");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/rule/list";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Rule>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				new ParameterizedTypeReference<List<Rule>>() {}
			);
		
		return response.getBody();
	}
	
	public Rule save(Rule rule) {
		log.info("save");
		log.debug("rule = " + rule);

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/rule/save";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<?> request = new HttpEntity<>(rule, headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Rule> response = restTemplate.exchange(
				url, 
				HttpMethod.POST, 
				request,
				Rule.class);
		
		return response.getBody();
	}
	
	public Rule findById(Integer id) {
		log.info("findById");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/rule/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Rule> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				Rule.class);
		
		return response.getBody();
	}
	
	public Rule deleteById(Integer id) {
		log.info("save");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/rule/delete/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Rule> response = restTemplate.exchange(
				url, 
				HttpMethod.DELETE, 
				request,
				Rule.class);
		
		return response.getBody();
	}
	
}
