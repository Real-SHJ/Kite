package com.wildbody.kite.DTO;

import lombok.Data;

@Data
public class Analysis implements Comparable<Analysis>{
	private int age;
	private String company;
	private int count;
	private String gender;
	@Override
	public int compareTo(Analysis a) {
		// TODO Auto-generated method stub
		
		return a.getCount()-this.count;
	}
}
