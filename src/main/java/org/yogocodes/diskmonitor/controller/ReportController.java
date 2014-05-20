package org.yogocodes.diskmonitor.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yogocodes.diskmonitor.dao.MonitorStatistics;
import org.yogocodes.diskmonitor.service.DiskMonitorService;
import org.yogocodes.diskmonitor.service.MonitorEvent;

@Controller
public class ReportController {

	@Autowired
	private DiskMonitorService diskMonitorService; 
	
	@RequestMapping(value="/statistics")
	public String viewReport(@RequestParam(value="date", defaultValue="now", required=false) String date, @RequestParam(value="page", required=false, defaultValue="0") int page, @RequestParam(value="size", required=false, defaultValue="10") int pageSize, Model model) {
	
		final List<MonitorEvent> monitorEvents = diskMonitorService.getMonitorEvents(null, page, pageSize);
		model.addAttribute("events", monitorEvents);
		return "statistics"; 
	}
	
	
	@RequestMapping(value="/hourlystatistics") 
	public String viewHourlyReport(@RequestParam(value="date", defaultValue="now", required=false) String date, @RequestParam(value="page", required=false, defaultValue="0") int page, @RequestParam(value="size", required=false, defaultValue="10") int pageSize, Model model) {
		
		Date dateValue = null;
		
		if( "now".equals(date) ) {
			dateValue = new Date(); 
		}
		
		List<MonitorStatistics> statistics = diskMonitorService.getHourlyStatistics(dateValue, page, pageSize);
		model.addAttribute("statistics", statistics); 

		return "report"; 
	}
	
	@RequestMapping(value="/dailystatistics") 
	public String viewDailyReport(@RequestParam(value="date", defaultValue="now", required=false) String date, @RequestParam(value="page", required=false, defaultValue="0") int page, @RequestParam(value="size", required=false, defaultValue="10") int pageSize, Model model) {
		
		Date dateValue = null;
		
		if( "now".equals(date) ) {
			dateValue = new Date(); 
		}
		
		List<MonitorStatistics> statistics = diskMonitorService.getDailyStatistics(dateValue, page, pageSize);
		model.addAttribute("statistics", statistics); 

		return "report"; 
	}	
}
