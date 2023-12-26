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
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.JwtToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurvePointProxy {

	private final CustomProperties props;
	private final JwtToken jwtToken;

	public List<CurvePoint> findAll() {
		log.info("findAll");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/curvePoint/list";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<CurvePoint>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				new ParameterizedTypeReference<List<CurvePoint>>() {}
			);
		
		return response.getBody();
	}
	
	public CurvePoint save(CurvePoint curvePoint) {
		log.info("save");
		log.debug("curvePoint = " + curvePoint);

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/curvePoint/save";

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<?> request = new HttpEntity<>(curvePoint, headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CurvePoint> response = restTemplate.exchange(
				url, 
				HttpMethod.POST, 
				request,
				CurvePoint.class);
		
		return response.getBody();
	}
	
	public CurvePoint findById(Integer id) {
		log.info("findById");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/curvePoint/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CurvePoint> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request,
				CurvePoint.class);
		
		return response.getBody();
	}
	
	public CurvePoint deleteById(Integer id) {
		log.info("save");

		String baseApiUrl = props.getApiUrl();
		String url = baseApiUrl + "/curvePoint/delete/" + id;

		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken.getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CurvePoint> response = restTemplate.exchange(
				url, 
				HttpMethod.DELETE, 
				request,
				CurvePoint.class);
		
		return response.getBody();
	}
	
}
