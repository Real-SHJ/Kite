package com.wildbody.kite.Service;

import java.util.List;

import com.wildbody.kite.DTO.Analysis;

public interface AnalysisService {
	public List<Analysis> analysisList(int age, String gender);
}
