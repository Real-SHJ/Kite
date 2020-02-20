package com.wildbody.kite.Repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wildbody.kite.DTO.Analysis;
@Repository
public class AnalysisRepositoryImpl implements AnalysisRepository {
	@Autowired
	SqlSession session;
	@Override
	public List<Analysis> analysisList(int age, String gender) {
		// TODO Auto-generated method stub
		Analysis a=new Analysis();
		a.setAge(age);
		a.setGender(gender);
		return session.selectList("kite.analysis.selectRankCount",a);
	}
}
