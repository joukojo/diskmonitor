package org.yogocodes.diskmonitor.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MonitorEvent {

	private final long executionTime;
	private final long createdTime; 

	public MonitorEvent(long executionTime) {
		this.executionTime = executionTime;
		this.createdTime = System.currentTimeMillis(); 
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public long getCreatedTime() {
		return createdTime;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
	
}
