package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.IpData;

@Service
public class IpDataService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${ipinfo.token}")
	public String inInfoToken;

	public IpData getIpDataFromIpInfo(String ipAddress) throws HttpClientErrorException, ResourceAccessException {

		if (ipAddress == null || ipAddress.isBlank() || "0:0:0:0:0:0:0:1".equals(ipAddress)
				|| "127.0.0.1".equals(ipAddress)) {
			ipAddress = "124.123.171.236";
		}
//			System.out.println(ipAddress);
		// https:
		// domain //api.ipinfo.io
		// ip address /lite/8.8.8.8
		// Security token /me?token=f5d62c31dd995f

		// api -> url, method type, input data, response, inpu data type
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String inputData = "";

		HttpEntity<Object> requestEntity = new HttpEntity<Object>(inputData, headers);
		String apiUrl = "https://ipinfo.io/" + ipAddress + "/json?token=" + inInfoToken;
		ResponseEntity<IpData> response = restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, IpData.class);
		IpData ipData = response.getBody();
		return ipData;

//			System.out.println(response.getBody());
	}

}
