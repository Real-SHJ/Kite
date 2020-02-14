package com.wildbody.kite.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestfulController {

	private final String Kakao_api_key = "43eac99c0fcd21901e11639633fa2cc0";

	@Autowired
	private RestTemplate restTemplate;

	public String getKakaoRef(String rtoken) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://kauth.kakao.com/oauth/token");

		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("grant_type", "refresh_token"));
		params.add(new BasicNameValuePair("client_id", Kakao_api_key));
		params.add(new BasicNameValuePair("refresh_token", rtoken));
		httpPost.setEntity(new UrlEncodedFormEntity(params));
		// 요청
		CloseableHttpResponse response = client.execute(httpPost);

		// 파싱
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String retToken = this.parseAccToken(br.readLine());
		client.close();
		return retToken;
	}

	private String parseAccToken(String response) {
		return response.split(":")[1].split(",")[0].replaceAll("\"", "");
	}
}
