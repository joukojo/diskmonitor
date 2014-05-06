package org.yogocodes.diskmonitor.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.yogocodes.diskmonitor.service.DiskMonitorService;

@Service
public class DiskMonitorScheduler {

	@Autowired
	private DiskMonitorService diskMonitorService; 
	
	private final Logger logger = LoggerFactory.getLogger(DiskMonitorScheduler.class); 
	
	@Scheduled(fixedDelay = 30000)
	public void scheduledMonitoring() {
		logger.debug("Starting the scheduled monitoring");
		diskMonitorService.monitorDisk();
		logger.debug("Scheduled monitoring completed");
	}
}
