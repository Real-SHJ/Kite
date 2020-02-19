package com.wildbody.kite.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wildbody.kite.DTO.Analysis;
import com.wildbody.kite.Service.AnalysisService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {
	@Autowired
	private AnalysisService as;

	@GetMapping("/searchRank")
	@ApiOperation(value="세대별성별 검색어 분류")
	public @ResponseBody ResponseEntity<Map<String,Object>> listAnalysis(){
		ResponseEntity<Map<String,Object>> resEntity=null;
		//[20,30,40,50],[남자:0,여자:1]
		List<Analysis> list = null;
		Map<String,Object> map =new TreeMap<String,Object>();
		//리스트가 들어왔다.. 정보들이 여기서 company별로 count를 합쳐야한다.
		long sum[][]=new long[4][2];
		for(int i=0;i<4;i++) {
			Arrays.fill(sum[i], 0L);
		}
		int[] age = {20,30,40,50};
		String[] gender= {"남자","여자"};
		for(int i=0;i<4;i++) {
			for(int j=0;j<2;j++) {
				list=as.analysisList(age[i], gender[j]);
				//여기 연령별 정렬하고 보내기
				System.out.println("쿠팡 언제나오냐고!!!!!!");
				map.put(""+age[i]+"대"+" "+gender[j]+" "+"검색어", list);
			}
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
		
	}
}
