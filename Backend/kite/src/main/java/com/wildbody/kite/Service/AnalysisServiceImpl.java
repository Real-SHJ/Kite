package com.wildbody.kite.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildbody.kite.DTO.Analysis;
import com.wildbody.kite.Repository.AnalysisRepository;
import com.wildbody.kite.Repository.ArticleRepository;

@Service
public class AnalysisServiceImpl implements AnalysisService {

	@Autowired
    private AnalysisRepository repo;
	
	@Override
	public List<Analysis> analysisList(int age, String gender) {
		return repo.analysisList(age, gender);
	}

}
