package com.wildbody.kite.Controller;

import com.wildbody.kite.Http.RestfulController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HttpController {

	@Autowired
	private RestfulController rest;

	@PostMapping("/kakao")
	public @ResponseBody ResponseEntity<Map<String, Object>> kakaortoken() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		boolean ret = false;
		Map<String, Object> map = new HashMap<>();
		String accessToken = null;
		try {
			accessToken = rest.getKakaoRef("vNAAT1NYq2lFzJSR61zP5JNQgXABT0svedjDUAopcBMAAAFv6g4oiw");
			ret = true;
			map.put("Authorization", accessToken);
			map.put("result", ret);
		} catch (IOException e) {
			e.printStackTrace();
			map.put("result", ret);
		}

		resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		return resEntity;
	}

}
