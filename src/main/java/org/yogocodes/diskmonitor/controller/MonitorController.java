package org.yogocodes.diskmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yogocodes.diskmonitor.service.DiskMonitorService;

@Controller
public class MonitorController {

	@Autowired
	private DiskMonitorService diskMonitorService;
	
	@RequestMapping(value="/monitor")
	public String view(Model model ) {
		long delta = diskMonitorService.monitorDisk();
		model.addAttribute("delta", delta);
		return "status"; 
	}
	
}
