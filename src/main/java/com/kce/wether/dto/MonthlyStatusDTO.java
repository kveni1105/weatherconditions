package com.kce.wether.dto;

public class MonthlyStatusDTO {
	
	private double min;
	private double max;
	private double median;
	
	public MonthlyStatusDTO(double min, double max, double median)
	{
		this.min = min;
		this.max = max;
		this.median = median;
	}
	
	public double getmin()
	{
		return min;
	}
	public double getmax()
	{
		return max;
	}
	public double getmedian()
	{
		return median;
	}
}
