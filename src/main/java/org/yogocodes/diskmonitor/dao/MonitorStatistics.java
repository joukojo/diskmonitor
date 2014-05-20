package org.yogocodes.diskmonitor.dao;

public class MonitorStatistics {

	private final String field; 
	private final Long mininum; 
	private final Double average; 
	private final Long maximum;
	private final Long count;
	
	public MonitorStatistics(String field, Long minimun, Double average, Long maximum, Long count) {
		this.field = field;
		mininum = minimun;
		this.average = average;
		this.maximum = maximum;
		this.count = count;
		
	}
	
	public String getField() {
		return field;
	}
	public Long getMininum() {
		return mininum;
	}
	public Double getAverage() {
		return average;
	}
	public Long getMaximum() {
		return maximum;
	}

	public Long getCount() {
		return count;
	} 
	
	
}
