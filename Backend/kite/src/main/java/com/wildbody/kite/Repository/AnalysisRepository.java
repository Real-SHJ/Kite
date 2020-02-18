package com.wildbody.kite.Repository;

import java.util.List;

import com.wildbody.kite.DTO.Analysis;

public interface AnalysisRepository {
	public List<Analysis> analysisList(int age, String gender);
}
