package org.yogocodes.diskmonitor.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("diskMonitorService")
public class DiskMonitorService {

	private final String lineSep = System.getProperty("line.separator");
	private final String pathSep = System.getProperty("file.separator");

	private String diskMonitorLogDirectory = pathSep + "tmp" + pathSep
			+ "diskmonitor";
	private int count = 5000;

	private final Logger logger = LoggerFactory
			.getLogger(DiskMonitorService.class);

	@PostConstruct
	public void createDirectoryStructure() {

		final File directory = new File(diskMonitorLogDirectory);
		try {
			FileUtils.forceMkdir(directory);
		} catch (IOException e) {
			logger.error("failed to create directory {}",
					diskMonitorLogDirectory, e);
		}

	}

	public long monitorDisk() {

		final List<String> lines = generateTextData();
		final MonitorEvent monitorEvent = writeLinesToFile(lines);
		logEvent(monitorEvent);
		return monitorEvent.getExecutionTime();
	}

	protected void logEvent(MonitorEvent monitorEvent) {
		final String logFileName = getLogFileName(monitorEvent);
		final File logFile = new File(logFileName);
		try {
			final StringBuilder builder = new StringBuilder();
			builder.append(monitorEvent.getCreatedTime());
			builder.append(',');
			builder.append(monitorEvent.getExecutionTime());
			builder.append(lineSep);

			final String line = builder.toString();
			FileUtils.write(logFile, line, true);
		} catch (IOException e) {
			logger.error("failed to log entry  {}", logFileName, e);
		}
	}

	private String getLogFileName(final MonitorEvent monitorEvent) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
		final Date now = new Date(monitorEvent.getCreatedTime());
		final String formattedDate = dateFormat.format(now);

		final String logFileName = diskMonitorLogDirectory + pathSep
				+ "diskmonitor-" + formattedDate + ".log";
		return logFileName;
	}

	protected List<String> generateTextData() {
		final String line = "abcdefghijklmnopqrstuvwxyz";

		final List<String> lines = new ArrayList<String>(count);
		for (int i = 0; i < count; i++) {
			lines.add(line);
		}
		return lines;
	}

	private MonitorEvent writeLinesToFile(final List<String> lines) {
		final String testDataFile = diskMonitorLogDirectory + pathSep
				+ "test.data";
		final File file = new File(testDataFile);
		long delta = 0L;
		try {
			final long start = System.currentTimeMillis();
			FileUtils.writeLines(file, lines);
			final long end = System.currentTimeMillis();
			delta = end - start;
		} catch (IOException e) {
			logger.error("failed to write data file: {}", testDataFile, e);
		} finally {
			FileUtils.deleteQuietly(file);
		}
		return new MonitorEvent(delta);
	}

}
