package org.yogocodes.diskmonitor.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MonitorEvent {

	private final long executionTime;
	private final long createdTime;
	private final long fileSize; 

	public MonitorEvent(long executionTime, long fileSize) {
		this.executionTime = executionTime;
		this.fileSize = fileSize;
		this.createdTime = System.currentTimeMillis(); 
	}

	public MonitorEvent(long executionTime, long fileSize, Date created) {
		this.executionTime = executionTime;
		this.fileSize = fileSize;
		this.createdTime = created.getTime(); 
	
	}

	public long getExecutionTime() {
		return executionTime;
	}
	
	public String getFormattedCreated() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY kk:mm:ss"); 
		return sdf.format(new Date(this.createdTime));
	}

	public long getCreatedTime() {
		return createdTime;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	public long getFileSize() {
		return fileSize;
	}
	
}
